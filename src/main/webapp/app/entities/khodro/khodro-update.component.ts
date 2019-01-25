import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKhodro } from 'app/shared/model/khodro.model';
import { KhodroService } from './khodro.service';

@Component({
    selector: 'jhi-khodro-update',
    templateUrl: './khodro-update.component.html'
})
export class KhodroUpdateComponent implements OnInit {
    khodro: IKhodro;
    isSaving: boolean;

    constructor(protected khodroService: KhodroService, protected activatedRoute: ActivatedRoute) {}

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

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IKhodro>>) {
        result.subscribe((res: HttpResponse<IKhodro>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
