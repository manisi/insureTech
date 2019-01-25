import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMohasebeSales } from 'app/shared/model/mohasebe-sales.model';
import { MohasebeSalesService } from './mohasebe-sales.service';
import { ITipKhodro } from 'app/shared/model/tip-khodro.model';
import { TipKhodroService } from 'app/entities/tip-khodro';

@Component({
    selector: 'insutech-mohasebe-sales-update',
    templateUrl: './mohasebe-sales-update.component.html'
})
export class MohasebeSalesUpdateComponent implements OnInit {
    mohasebeSales: IMohasebeSales;
    isSaving: boolean;

    tipkhodros: ITipKhodro[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected mohasebeSalesService: MohasebeSalesService,
        protected tipKhodroService: TipKhodroService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mohasebeSales }) => {
            this.mohasebeSales = mohasebeSales;
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
        if (this.mohasebeSales.id !== undefined) {
            this.subscribeToSaveResponse(this.mohasebeSalesService.update(this.mohasebeSales));
        } else {
            this.subscribeToSaveResponse(this.mohasebeSalesService.create(this.mohasebeSales));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMohasebeSales>>) {
        result.subscribe((res: HttpResponse<IMohasebeSales>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
