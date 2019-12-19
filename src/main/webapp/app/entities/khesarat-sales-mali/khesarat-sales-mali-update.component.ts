import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IKhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';
import { KhesaratSalesMaliService } from './khesarat-sales-mali.service';
import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from 'app/entities/sabeghe';
import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';
import { NoeSabegheService } from 'app/entities/noe-sabeghe';

@Component({
    selector: 'jhi-khesarat-sales-mali-update',
    templateUrl: './khesarat-sales-mali-update.component.html'
})
export class KhesaratSalesMaliUpdateComponent implements OnInit {
    khesaratSalesMali: IKhesaratSalesMali;
    isSaving: boolean;

    sabeghes: ISabeghe[];

    noesabeghes: INoeSabeghe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected khesaratSalesMaliService: KhesaratSalesMaliService,
        protected sabegheService: SabegheService,
        protected noeSabegheService: NoeSabegheService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ khesaratSalesMali }) => {
            this.khesaratSalesMali = khesaratSalesMali;
        });
        this.sabegheService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISabeghe[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISabeghe[]>) => response.body)
            )
            .subscribe((res: ISabeghe[]) => (this.sabeghes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.noeSabegheService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<INoeSabeghe[]>) => mayBeOk.ok),
                map((response: HttpResponse<INoeSabeghe[]>) => response.body)
            )
            .subscribe((res: INoeSabeghe[]) => (this.noesabeghes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.khesaratSalesMali.id !== undefined) {
            this.subscribeToSaveResponse(this.khesaratSalesMaliService.update(this.khesaratSalesMali));
        } else {
            this.subscribeToSaveResponse(this.khesaratSalesMaliService.create(this.khesaratSalesMali));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IKhesaratSalesMali>>) {
        result.subscribe((res: HttpResponse<IKhesaratSalesMali>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSabegheById(index: number, item: ISabeghe) {
        return item.id;
    }

    trackNoeSabegheById(index: number, item: INoeSabeghe) {
        return item.id;
    }
}
