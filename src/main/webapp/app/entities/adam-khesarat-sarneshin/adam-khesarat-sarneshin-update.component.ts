import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';
import { AdamKhesaratSarneshinService } from './adam-khesarat-sarneshin.service';
import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';
import { NoeSabegheService } from 'app/entities/noe-sabeghe';
import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from 'app/entities/sabeghe';

@Component({
    selector: 'jhi-adam-khesarat-sarneshin-update',
    templateUrl: './adam-khesarat-sarneshin-update.component.html'
})
export class AdamKhesaratSarneshinUpdateComponent implements OnInit {
    adamKhesaratSarneshin: IAdamKhesaratSarneshin;
    isSaving: boolean;

    noesabeghes: INoeSabeghe[];

    sabeghes: ISabeghe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected adamKhesaratSarneshinService: AdamKhesaratSarneshinService,
        protected noeSabegheService: NoeSabegheService,
        protected sabegheService: SabegheService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ adamKhesaratSarneshin }) => {
            this.adamKhesaratSarneshin = adamKhesaratSarneshin;
        });
        this.noeSabegheService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<INoeSabeghe[]>) => mayBeOk.ok),
                map((response: HttpResponse<INoeSabeghe[]>) => response.body)
            )
            .subscribe((res: INoeSabeghe[]) => (this.noesabeghes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.sabegheService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISabeghe[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISabeghe[]>) => response.body)
            )
            .subscribe((res: ISabeghe[]) => (this.sabeghes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.adamKhesaratSarneshin.id !== undefined) {
            this.subscribeToSaveResponse(this.adamKhesaratSarneshinService.update(this.adamKhesaratSarneshin));
        } else {
            this.subscribeToSaveResponse(this.adamKhesaratSarneshinService.create(this.adamKhesaratSarneshin));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdamKhesaratSarneshin>>) {
        result.subscribe(
            (res: HttpResponse<IAdamKhesaratSarneshin>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackNoeSabegheById(index: number, item: INoeSabeghe) {
        return item.id;
    }

    trackSabegheById(index: number, item: ISabeghe) {
        return item.id;
    }
}
