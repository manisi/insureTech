import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IKohnegi } from 'app/shared/model/kohnegi.model';
import { KohnegiService } from './kohnegi.service';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from 'app/entities/grouh-khodro';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from 'app/entities/sherkat-bime';

@Component({
    selector: 'jhi-kohnegi-update',
    templateUrl: './kohnegi-update.component.html'
})
export class KohnegiUpdateComponent implements OnInit {
    kohnegi: IKohnegi;
    isSaving: boolean;

    grouhkhodros: IGrouhKhodro[];

    sherkatbimes: ISherkatBime[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected kohnegiService: KohnegiService,
        protected grouhKhodroService: GrouhKhodroService,
        protected sherkatBimeService: SherkatBimeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ kohnegi }) => {
            this.kohnegi = kohnegi;
        });
        this.grouhKhodroService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IGrouhKhodro[]>) => mayBeOk.ok),
                map((response: HttpResponse<IGrouhKhodro[]>) => response.body)
            )
            .subscribe((res: IGrouhKhodro[]) => (this.grouhkhodros = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.sherkatBimeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISherkatBime[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISherkatBime[]>) => response.body)
            )
            .subscribe((res: ISherkatBime[]) => (this.sherkatbimes = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        if (this.kohnegi.id !== undefined) {
            this.subscribeToSaveResponse(this.kohnegiService.update(this.kohnegi));
        } else {
            this.subscribeToSaveResponse(this.kohnegiService.create(this.kohnegi));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IKohnegi>>) {
        result.subscribe((res: HttpResponse<IKohnegi>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSherkatBimeById(index: number, item: ISherkatBime) {
        return item.id;
    }
}
