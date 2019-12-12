import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';
import { EstelaamSalesNerkhService } from './estelaam-sales-nerkh.service';
import { IAnvaeKhodro } from 'app/shared/model/anvae-khodro.model';
import { AnvaeKhodroService } from 'app/entities/anvae-khodro';
import { ISaalSakht } from 'app/shared/model/saal-sakht.model';
import { SaalSakhtService } from 'app/entities/saal-sakht';
import { IOnvanKhodro } from 'app/shared/model/onvan-khodro.model';
import { OnvanKhodroService } from 'app/entities/onvan-khodro';
import { IAdamKhesarat } from 'app/shared/model/adam-khesarat.model';
import { AdamKhesaratService } from 'app/entities/adam-khesarat';
import { IAdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';
import { AdamKhesaratSarneshinService } from 'app/entities/adam-khesarat-sarneshin';
import { IKhesaratSrneshin } from 'app/shared/model/khesarat-srneshin.model';
import { KhesaratSrneshinService } from 'app/entities/khesarat-srneshin';
import { IKhesaratSales } from 'app/shared/model/khesarat-sales.model';
import { KhesaratSalesService } from 'app/entities/khesarat-sales';

@Component({
    selector: 'jhi-estelaam-sales-nerkh-update',
    templateUrl: './estelaam-sales-nerkh-update.component.html'
})
export class EstelaamSalesNerkhUpdateComponent implements OnInit {
    estelaamSalesNerkh: IEstelaamSalesNerkh;
    isSaving: boolean;
    anvaekhodros: IAnvaeKhodro[];
    saalsakhts: ISaalSakht[];
    onvankhodros: IOnvanKhodro[];
    adamkhesarats: IAdamKhesarat[];
    adamkhesaratsarneshins: IAdamKhesaratSarneshin[];
    khesaratsrneshins: IKhesaratSrneshin[];
    khesaratsales: IKhesaratSales[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected estelaamSalesNerkhService: EstelaamSalesNerkhService,
        protected anvaeKhodroService: AnvaeKhodroService,
        protected saalSakhtService: SaalSakhtService,
        protected onvanKhodroService: OnvanKhodroService,
        protected adamKhesaratService: AdamKhesaratService,
        protected adamKhesaratSarneshinService: AdamKhesaratSarneshinService,
        protected khesaratSrneshinService: KhesaratSrneshinService,
        protected khesaratSalesService: KhesaratSalesService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ estelaamSalesNerkh }) => {
            this.estelaamSalesNerkh = estelaamSalesNerkh;
        });
        this.anvaeKhodroService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAnvaeKhodro[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAnvaeKhodro[]>) => response.body)
            )
            .subscribe((res: IAnvaeKhodro[]) => (this.anvaekhodros = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.saalSakhtService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISaalSakht[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISaalSakht[]>) => response.body)
            )
            .subscribe((res: ISaalSakht[]) => (this.saalsakhts = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.onvanKhodroService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IOnvanKhodro[]>) => mayBeOk.ok),
                map((response: HttpResponse<IOnvanKhodro[]>) => response.body)
            )
            .subscribe((res: IOnvanKhodro[]) => (this.onvankhodros = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.adamKhesaratService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAdamKhesarat[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAdamKhesarat[]>) => response.body)
            )
            .subscribe((res: IAdamKhesarat[]) => (this.adamkhesarats = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.adamKhesaratSarneshinService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAdamKhesaratSarneshin[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAdamKhesaratSarneshin[]>) => response.body)
            )
            .subscribe(
                (res: IAdamKhesaratSarneshin[]) => (this.adamkhesaratsarneshins = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.khesaratSrneshinService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IKhesaratSrneshin[]>) => mayBeOk.ok),
                map((response: HttpResponse<IKhesaratSrneshin[]>) => response.body)
            )
            .subscribe((res: IKhesaratSrneshin[]) => (this.khesaratsrneshins = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.khesaratSalesService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IKhesaratSales[]>) => mayBeOk.ok),
                map((response: HttpResponse<IKhesaratSales[]>) => response.body)
            )
            .subscribe((res: IKhesaratSales[]) => (this.khesaratsales = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.estelaamSalesNerkh.id !== undefined) {
            this.subscribeToSaveResponse(this.estelaamSalesNerkhService.update(this.estelaamSalesNerkh));
        } else {
            this.subscribeToSaveResponse(this.estelaamSalesNerkhService.create(this.estelaamSalesNerkh));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstelaamSalesNerkh>>) {
        result.subscribe((res: HttpResponse<IEstelaamSalesNerkh>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAnvaeKhodroById(index: number, item: IAnvaeKhodro) {
        return item.id;
    }

    trackSaalSakhtById(index: number, item: ISaalSakht) {
        return item.id;
    }

    trackOnvanKhodroById(index: number, item: IOnvanKhodro) {
        return item.id;
    }

    trackAdamKhesaratById(index: number, item: IAdamKhesarat) {
        return item.id;
    }

    trackAdamKhesaratSarneshinById(index: number, item: IAdamKhesaratSarneshin) {
        return item.id;
    }

    trackKhesaratSrneshinById(index: number, item: IKhesaratSrneshin) {
        return item.id;
    }

    trackKhesaratSalesById(index: number, item: IKhesaratSales) {
        return item.id;
    }
}
