import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKhodroBimisho } from 'app/shared/model/khodro-bimisho.model';
import { KhodroBimishoService } from './khodro-bimisho.service';

@Component({
    selector: 'insutech-khodro-bimisho-update',
    templateUrl: './khodro-bimisho-update.component.html'
})
export class KhodroBimishoUpdateComponent implements OnInit {
    khodro: IKhodroBimisho;
    isSaving: boolean;

    constructor(protected khodroService: KhodroBimishoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ khodro }) => {
            this.khodro = khodro;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.khodro.id !== undefined) {
            this.subscribeToSaveResponse(this.khodroService.update(this.khodro));
        } else {
            this.subscribeToSaveResponse(this.khodroService.create(this.khodro));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IKhodroBimisho>>) {
        result.subscribe((res: HttpResponse<IKhodroBimisho>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
