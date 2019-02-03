import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IKohnegiBadane } from 'app/shared/model/kohnegi-badane.model';
import { KohnegiBadaneService } from './kohnegi-badane.service';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from 'app/entities/grouh-khodro';

@Component({
    selector: 'jhi-kohnegi-badane-update',
    templateUrl: './kohnegi-badane-update.component.html'
})
export class KohnegiBadaneUpdateComponent implements OnInit {
    kohnegiBadane: IKohnegiBadane;
    isSaving: boolean;

    grouhkhodros: IGrouhKhodro[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected kohnegiBadaneService: KohnegiBadaneService,
        protected grouhKhodroService: GrouhKhodroService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ kohnegiBadane }) => {
            this.kohnegiBadane = kohnegiBadane;
        });
        this.grouhKhodroService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IGrouhKhodro[]>) => mayBeOk.ok),
                map((response: HttpResponse<IGrouhKhodro[]>) => response.body)
            )
            .subscribe((res: IGrouhKhodro[]) => (this.grouhkhodros = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.kohnegiBadane.id !== undefined) {
            this.subscribeToSaveResponse(this.kohnegiBadaneService.update(this.kohnegiBadane));
        } else {
            this.subscribeToSaveResponse(this.kohnegiBadaneService.create(this.kohnegiBadane));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IKohnegiBadane>>) {
        result.subscribe((res: HttpResponse<IKohnegiBadane>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackGrouhKhodroById(index: number, item: IGrouhKhodro) {
        return item.id;
    }
}
