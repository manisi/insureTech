import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAdamKhesarat } from 'app/shared/model/adam-khesarat.model';
import { AdamKhesaratService } from './adam-khesarat.service';
import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from 'app/entities/sabeghe';
import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';
import { NoeSabegheService } from 'app/entities/noe-sabeghe';

@Component({
    selector: 'jhi-adam-khesarat-update',
    templateUrl: './adam-khesarat-update.component.html'
})
export class AdamKhesaratUpdateComponent implements OnInit {
    adamKhesarat: IAdamKhesarat;
    isSaving: boolean;

    sabeghes: ISabeghe[];

    noesabeghes: INoeSabeghe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected adamKhesaratService: AdamKhesaratService,
        protected sabegheService: SabegheService,
        protected noeSabegheService: NoeSabegheService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ adamKhesarat }) => {
            this.adamKhesarat = adamKhesarat;
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
        if (this.adamKhesarat.id !== undefined) {
            this.subscribeToSaveResponse(this.adamKhesaratService.update(this.adamKhesarat));
        } else {
            this.subscribeToSaveResponse(this.adamKhesaratService.create(this.adamKhesarat));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdamKhesarat>>) {
        result.subscribe((res: HttpResponse<IAdamKhesarat>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
