import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { ISalesMazadCalc } from 'app/shared/model/sales-mazad-calc.model';
import { SalesMazadCalcService } from './sales-mazad-calc.service';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from 'app/entities/sherkat-bime';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from 'app/entities/grouh-khodro';

@Component({
    selector: 'jhi-sales-mazad-calc-update',
    templateUrl: './sales-mazad-calc-update.component.html'
})
export class SalesMazadCalcUpdateComponent implements OnInit {
    salesMazadCalc: ISalesMazadCalc;
    isSaving: boolean;

    sherkatbimes: ISherkatBime[];

    grouhkhodros: IGrouhKhodro[];
    tarikhShorooDp: any;
    tarikhPayanDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected salesMazadCalcService: SalesMazadCalcService,
        protected sherkatBimeService: SherkatBimeService,
        protected grouhKhodroService: GrouhKhodroService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ salesMazadCalc }) => {
            this.salesMazadCalc = salesMazadCalc;
        });
        this.sherkatBimeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISherkatBime[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISherkatBime[]>) => response.body)
            )
            .subscribe((res: ISherkatBime[]) => (this.sherkatbimes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.grouhKhodroService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IGrouhKhodro[]>) => mayBeOk.ok),
                map((response: HttpResponse<IGrouhKhodro[]>) => response.body)
            )
            .subscribe((res: IGrouhKhodro[]) => (this.grouhkhodros = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.salesMazadCalc.id !== undefined) {
            this.subscribeToSaveResponse(this.salesMazadCalcService.update(this.salesMazadCalc));
        } else {
            this.subscribeToSaveResponse(this.salesMazadCalcService.create(this.salesMazadCalc));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISalesMazadCalc>>) {
        result.subscribe((res: HttpResponse<ISalesMazadCalc>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSherkatBimeById(index: number, item: ISherkatBime) {
        return item.id;
    }

    trackGrouhKhodroById(index: number, item: IGrouhKhodro) {
        return item.id;
    }
}
