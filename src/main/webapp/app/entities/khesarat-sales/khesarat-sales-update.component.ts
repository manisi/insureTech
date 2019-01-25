import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IKhesaratSales } from 'app/shared/model/khesarat-sales.model';
import { KhesaratSalesService } from './khesarat-sales.service';
import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from 'app/entities/sabeghe';
import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';
import { NoeSabegheService } from 'app/entities/noe-sabeghe';

@Component({
    selector: 'jhi-khesarat-sales-update',
    templateUrl: './khesarat-sales-update.component.html'
})
export class KhesaratSalesUpdateComponent implements OnInit {
    khesaratSales: IKhesaratSales;
    isSaving: boolean;

    sabeghes: ISabeghe[];

    noesabeghes: INoeSabeghe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected khesaratSalesService: KhesaratSalesService,
        protected sabegheService: SabegheService,
        protected noeSabegheService: NoeSabegheService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ khesaratSales }) => {
            this.khesaratSales = khesaratSales;
        });
        this.sabegheService.query().subscribe(
            (res: HttpResponse<ISabeghe[]>) => {
                this.sabeghes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.noeSabegheService.query().subscribe(
            (res: HttpResponse<INoeSabeghe[]>) => {
                this.noesabeghes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.khesaratSales.id !== undefined) {
            this.subscribeToSaveResponse(this.khesaratSalesService.update(this.khesaratSales));
        } else {
            this.subscribeToSaveResponse(this.khesaratSalesService.create(this.khesaratSales));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IKhesaratSales>>) {
        result.subscribe((res: HttpResponse<IKhesaratSales>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
