import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { ISalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';
import { SalesSarneshinCalcService } from './sales-sarneshin-calc.service';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from 'app/entities/sherkat-bime';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from 'app/entities/grouh-khodro';

@Component({
    selector: 'jhi-sales-sarneshin-calc-update',
    templateUrl: './sales-sarneshin-calc-update.component.html'
})
export class SalesSarneshinCalcUpdateComponent implements OnInit {
    salesSarneshinCalc: ISalesSarneshinCalc;
    isSaving: boolean;

    sherkatbimes: ISherkatBime[];

    grouhkhodros: IGrouhKhodro[];
    tarikhShorooDp: any;
    tarikhPayanDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected salesSarneshinCalcService: SalesSarneshinCalcService,
        protected sherkatBimeService: SherkatBimeService,
        protected grouhKhodroService: GrouhKhodroService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ salesSarneshinCalc }) => {
            this.salesSarneshinCalc = salesSarneshinCalc;
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
        if (this.salesSarneshinCalc.id !== undefined) {
            this.subscribeToSaveResponse(this.salesSarneshinCalcService.update(this.salesSarneshinCalc));
        } else {
            this.subscribeToSaveResponse(this.salesSarneshinCalcService.create(this.salesSarneshinCalc));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISalesSarneshinCalc>>) {
        result.subscribe((res: HttpResponse<ISalesSarneshinCalc>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
