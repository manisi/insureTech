import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMohasebeBadane } from 'app/shared/model/mohasebe-badane.model';
import { MohasebeBadaneService } from './mohasebe-badane.service';
import { ITipKhodro } from 'app/shared/model/tip-khodro.model';
import { TipKhodroService } from 'app/entities/tip-khodro';

@Component({
    selector: 'jhi-mohasebe-badane-update',
    templateUrl: './mohasebe-badane-update.component.html'
})
export class MohasebeBadaneUpdateComponent implements OnInit {
    mohasebeBadane: IMohasebeBadane;
    isSaving: boolean;

    tipkhodros: ITipKhodro[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected mohasebeBadaneService: MohasebeBadaneService,
        protected tipKhodroService: TipKhodroService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mohasebeBadane }) => {
            this.mohasebeBadane = mohasebeBadane;
        });
        this.tipKhodroService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITipKhodro[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITipKhodro[]>) => response.body)
            )
            .subscribe((res: ITipKhodro[]) => (this.tipkhodros = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mohasebeBadane.id !== undefined) {
            this.subscribeToSaveResponse(this.mohasebeBadaneService.update(this.mohasebeBadane));
        } else {
            this.subscribeToSaveResponse(this.mohasebeBadaneService.create(this.mohasebeBadane));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMohasebeBadane>>) {
        result.subscribe((res: HttpResponse<IMohasebeBadane>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTipKhodroById(index: number, item: ITipKhodro) {
        return item.id;
    }
}
