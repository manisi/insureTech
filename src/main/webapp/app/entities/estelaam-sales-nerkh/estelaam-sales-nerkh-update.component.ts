import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';
import { EstelaamSalesNerkhService } from './estelaam-sales-nerkh.service';

@Component({
    selector: 'jhi-estelaam-sales-nerkh-update',
    templateUrl: './estelaam-sales-nerkh-update.component.html'
})
export class EstelaamSalesNerkhUpdateComponent implements OnInit {
    estelaamSalesNerkh: IEstelaamSalesNerkh;
    isSaving: boolean;

    constructor(protected estelaamSalesNerkhService: EstelaamSalesNerkhService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ estelaamSalesNerkh }) => {
            this.estelaamSalesNerkh = estelaamSalesNerkh;
        });
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
}
