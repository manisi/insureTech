import { NgbTabsetConfig } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnvaeKhodroService } from 'app/entities/anvae-khodro/index';
import { IAnvaeKhodro } from 'app/shared/model/anvae-khodro.model';
import { filter, map } from 'rxjs/operators';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { JhiParseLinks, JhiAlertService } from 'ng-jhipster';
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
import { IEstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';
import { SalesNerkhData } from 'app/hi-there/nerkhSales.model';
import { NerkhSalesService } from 'app/hi-there/nerkhsales.service';
import { ITEMS_PER_PAGE } from 'app/shared';
import { SherkatBimeService } from 'app/entities/sherkat-bime/sherkat-bime.service';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { Moment } from 'jalali-moment';
import { VaziatBimeService } from 'app/entities/vaziat-bime/vaziat-bime.service';
import { IVaziatBime } from 'app/shared/model/vaziat-bime.model';
import { ModateBimenameService } from 'app/entities/modate-bimename/modate-bimename.service';
import { IModateBimename } from 'app/shared/model/modate-bimename.model';
import { SabegheKhesaratService } from 'app/entities/sabeghe-khesarat/sabeghe-khesarat.service';
import { ISabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';

@Component({
    selector: 'jhi-hi-there',
    templateUrl: './hi-there.component.html',
    styleUrls: ['hi-there.component.scss']
})
export class HiThereComponent implements OnInit {
    message: string;
    estelaamSalesNerkh: IEstelaamSalesNerkh;
    anvaekhodros: IAnvaeKhodro[];
    saalsakhts: ISaalSakht[];
    vaziatBimes: IVaziatBime[];
    onvankhodros: IOnvanKhodro[];
    sherkatebimes: ISherkatBime[];
    modateBimenames: IModateBimename[];
    sabegheKhesarats: ISabegheKhesarat[];
    adamkhesarats: IAdamKhesarat[];
    adamkhesaratsarneshins: IAdamKhesaratSarneshin[];
    khesaratsrneshins: IKhesaratSrneshin[];
    khesaratsales: IKhesaratSales[];
    khesaratSalesmalis: IKhesaratSales[];
    isSaving: boolean;
    salesnerkhs: SalesNerkhData[];
    anvaeKhodro: string;
    sherkatBime: string;
    tarikhEtebar: Moment;
    vaziatBime: string;
    saalSakht: string;
    modateBimename: string;
    sabegheKhesarat: string;
    codeyekta: string;
    onvanKhodro: string;
    adamKhesarat: string;
    adamKhesaratSarneshin: string;
    khesaratSrneshin: string;
    khesaratSales: string;
    khesaratSalesmali: string;
    itemsPerPage: any;
    links: any;
    totalItems: number;
    page: number;
    routeData: any;
    predicate: any;
    previousPage: any;
    reverse: boolean;
    requiredtext: string;

    constructor(
        config: NgbTabsetConfig,
        private router: Router,
        protected anvaeKhodroService: AnvaeKhodroService,
        protected jhiAlertService: JhiAlertService,
        private parseLinks: JhiParseLinks,
        protected saalSakhtService: SaalSakhtService,
        protected onvanKhodroService: OnvanKhodroService,
        protected vaziatBimeService: VaziatBimeService,
        protected modateBimenameService: ModateBimenameService,
        protected sabegheKhesaratService: SabegheKhesaratService,
        protected sherkatBimeService: SherkatBimeService,
        protected adamKhesaratService: AdamKhesaratService,
        protected adamKhesaratSarneshinService: AdamKhesaratSarneshinService,
        protected khesaratSrneshinService: KhesaratSrneshinService,
        protected khesaratSalesService: KhesaratSalesService,
        private nerkhsalesService: NerkhSalesService,
        private activatedRoute: ActivatedRoute
    ) {
        this.message = 'HiThereComponent message';
        this.anvaeKhodroService;
        // this.itemsPerPage = ITEMS_PER_PAGE;
        // this.routeData = this.activatedRoute.data.subscribe(data => {
        //     this.page = data['pagingParams'].page;
        //     this.previousPage = data['pagingParams'].page;
        //     this.reverse = data['pagingParams'].ascending;
        //     this.predicate = data['pagingParams'].predicate;
        // });
        config.justify = 'justified';
        // fill,start,end,center
        config.orientation = 'horizontal';
        config.type = 'pills';
    }

    ngOnInit() {
        this.isSaving = false;
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
        this.vaziatBimeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IVaziatBime[]>) => mayBeOk.ok),
                map((response: HttpResponse<IVaziatBime[]>) => response.body)
            )
            .subscribe((res: IVaziatBime[]) => (this.vaziatBimes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.modateBimenameService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IModateBimename[]>) => mayBeOk.ok),
                map((response: HttpResponse<IModateBimename[]>) => response.body)
            )
            .subscribe((res: IModateBimename[]) => (this.modateBimenames = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.sabegheKhesaratService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISabegheKhesarat[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISabegheKhesarat[]>) => response.body)
            )
            .subscribe((res: ISabegheKhesarat[]) => (this.sabegheKhesarats = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.sherkatBimeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISherkatBime[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISherkatBime[]>) => response.body)
            )
            .subscribe((res: ISherkatBime[]) => (this.sherkatebimes = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.khesaratSalesService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IKhesaratSales[]>) => mayBeOk.ok),
                map((response: HttpResponse<IKhesaratSales[]>) => response.body)
            )
            .subscribe((res: IKhesaratSales[]) => (this.khesaratSalesmalis = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    loadAll() {
        this.nerkhsalesService
            .query({
                // page: this.page - 1,
                //  size: this.itemsPerPage,
                //  sort: this.sort(),
                // fromDate: this.fromDate,
                // toDate: this.toDate
                anvaeKhodro: this.anvaeKhodro,
                saalSakht: this.saalSakht,
                vaziatBime: this.vaziatBime,
                onvanKhodro: this.onvanKhodro,
                adamKhesarat: this.adamKhesarat,
                adamKhesaratSarneshin: this.adamKhesaratSarneshin,
                khesaratSrneshin: this.khesaratSrneshin,
                khesaratSales: this.khesaratSales,
                sherkatBime: this.sherkatBime,
                tarikhEtebar: this.tarikhEtebar,
                codeyekta: this.codeyekta,
                modateBimename: this.modateBimename,
                sabegheKhesarat: this.sabegheKhesarat
            })
            .subscribe(
                (res: HttpResponse<SalesNerkhData[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpResponse<any>) => this.onError(res.body)
            );
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        // this.router.navigate(['/api/hi-there'], {
        //     queryParams: {
        //        page: this.page,
        //        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        //     }
        // });
        this.loadAll();
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.salesnerkhs = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    requestResetPassword() {
        this.router.navigate(['/reset', 'request']);
    }

    previousState() {
        window.history.back();
    }
    //
    //
    // changeRequiredType() {
    //     // server port
    //     if (this.vaziatBime === '13601') {
    //         this.requiredtext = 'required';
    //     // } else if (this.vaziatBime === 'uaa') {
    //     //    // this.vaziatBime= 9999;
    //     } else {
    //        this.requiredtext= '';
    //         }
    //
    // }

    save() {
        this.isSaving = true;
        // if (this.estelaamSalesNerkh.id !== undefined) {
        //     this.subscribeToSaveResponse(this.estelaamSalesNerkhService.update(this.estelaamSalesNerkh));
        // } else {
        //     this.subscribeToSaveResponse(this.estelaamSalesNerkhService.create(this.estelaamSalesNerkh));
        // }
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

    trackSherkatBimeById(index: number, item: ISherkatBime) {
        return item.id;
    }
    trackVaziatBimeById(index: number, item: IVaziatBime) {
        return item.id;
    }
    trackModateBimenameById(index: number, item: IModateBimename) {
        return item.id;
    }
    trackSabegheKhesaratById(index: number, item: ISabegheKhesarat) {
        return item.id;
    }
}
