import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';
import { AdamKhesaratBadaneService } from './adam-khesarat-badane.service';
import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';
import { NoeSabegheService } from 'app/entities/noe-sabeghe';
import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from 'app/entities/sabeghe';

@Component({
    selector: 'jhi-adam-khesarat-badane-update',
    templateUrl: './adam-khesarat-badane-update.component.html'
})
export class AdamKhesaratBadaneUpdateComponent implements OnInit {
    adamKhesaratBadane: IAdamKhesaratBadane;
    isSaving: boolean;

    noesabeghes: INoeSabeghe[];

    sabeghes: ISabeghe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected adamKhesaratBadaneService: AdamKhesaratBadaneService,
        protected noeSabegheService: NoeSabegheService,
        protected sabegheService: SabegheService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ adamKhesaratBadane }) => {
            this.adamKhesaratBadane = adamKhesaratBadane;
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
        if (this.adamKhesaratBadane.id !== undefined) {
            this.subscribeToSaveResponse(this.adamKhesaratBadaneService.update(this.adamKhesaratBadane));
        } else {
            this.subscribeToSaveResponse(this.adamKhesaratBadaneService.create(this.adamKhesaratBadane));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdamKhesaratBadane>>) {
        result.subscribe((res: HttpResponse<IAdamKhesaratBadane>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
