import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITipKhodroBimisho } from 'app/shared/model/tip-khodro-bimisho.model';
import { TipKhodroBimishoService } from './tip-khodro-bimisho.service';
import { IKhodroBimisho } from 'app/shared/model/khodro-bimisho.model';
import { KhodroBimishoService } from 'app/entities/khodro-bimisho';

@Component({
    selector: 'insutech-tip-khodro-bimisho-update',
    templateUrl: './tip-khodro-bimisho-update.component.html'
})
export class TipKhodroBimishoUpdateComponent implements OnInit {
    tipKhodro: ITipKhodroBimisho;
    isSaving: boolean;

    khodros: IKhodroBimisho[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected tipKhodroService: TipKhodroBimishoService,
        protected khodroService: KhodroBimishoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipKhodro }) => {
            this.tipKhodro = tipKhodro;
        });
        this.khodroService.query().subscribe(
            (res: HttpResponse<IKhodroBimisho[]>) => {
                this.khodros = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipKhodro.id !== undefined) {
            this.subscribeToSaveResponse(this.tipKhodroService.update(this.tipKhodro));
        } else {
            this.subscribeToSaveResponse(this.tipKhodroService.create(this.tipKhodro));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipKhodroBimisho>>) {
        result.subscribe((res: HttpResponse<ITipKhodroBimisho>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackKhodroById(index: number, item: IKhodroBimisho) {
        return item.id;
    }
}
