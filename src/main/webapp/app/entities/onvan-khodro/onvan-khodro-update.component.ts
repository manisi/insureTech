import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { IOnvanKhodro } from 'app/shared/model/onvan-khodro.model';
import { OnvanKhodroService } from './onvan-khodro.service';

@Component({
    selector: 'jhi-onvan-khodro-update',
    templateUrl: './onvan-khodro-update.component.html'
})
export class OnvanKhodroUpdateComponent implements OnInit {
    onvanKhodro: IOnvanKhodro;
    isSaving: boolean;
    azTarikhDp: any;
    taTarikhDp: any;

    constructor(protected onvanKhodroService: OnvanKhodroService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ onvanKhodro }) => {
            this.onvanKhodro = onvanKhodro;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.onvanKhodro.id !== undefined) {
            this.subscribeToSaveResponse(this.onvanKhodroService.update(this.onvanKhodro));
        } else {
            this.subscribeToSaveResponse(this.onvanKhodroService.create(this.onvanKhodro));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnvanKhodro>>) {
        result.subscribe((res: HttpResponse<IOnvanKhodro>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
