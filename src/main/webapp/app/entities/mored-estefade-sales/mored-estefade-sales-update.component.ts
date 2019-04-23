import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IMoredEstefadeSales } from 'app/shared/model/mored-estefade-sales.model';
import { MoredEstefadeSalesService } from './mored-estefade-sales.service';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from 'app/entities/grouh-khodro';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from 'app/entities/sherkat-bime';

@Component({
    selector: 'jhi-mored-estefade-sales-update',
    templateUrl: './mored-estefade-sales-update.component.html'
})
export class MoredEstefadeSalesUpdateComponent implements OnInit {
    moredEstefadeSales: IMoredEstefadeSales;
    isSaving: boolean;

    grouhkhodros: IGrouhKhodro[];

    sherkatbimes: ISherkatBime[];
    azTarikhDp: any;
    taTarikhDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected moredEstefadeSalesService: MoredEstefadeSalesService,
        protected grouhKhodroService: GrouhKhodroService,
        protected sherkatBimeService: SherkatBimeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ moredEstefadeSales }) => {
            this.moredEstefadeSales = moredEstefadeSales;
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.moredEstefadeSales.id !== undefined) {
            this.subscribeToSaveResponse(this.moredEstefadeSalesService.update(this.moredEstefadeSales));
        } else {
            this.subscribeToSaveResponse(this.moredEstefadeSalesService.create(this.moredEstefadeSales));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMoredEstefadeSales>>) {
        result.subscribe((res: HttpResponse<IMoredEstefadeSales>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
