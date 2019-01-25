import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from './grouh-khodro.service';

@Component({
    selector: 'jhi-grouh-khodro-update',
    templateUrl: './grouh-khodro-update.component.html'
})
export class GrouhKhodroUpdateComponent implements OnInit {
    grouhKhodro: IGrouhKhodro;
    isSaving: boolean;

    constructor(protected grouhKhodroService: GrouhKhodroService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ grouhKhodro }) => {
            this.grouhKhodro = grouhKhodro;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.grouhKhodro.id !== undefined) {
            this.subscribeToSaveResponse(this.grouhKhodroService.update(this.grouhKhodro));
        } else {
            this.subscribeToSaveResponse(this.grouhKhodroService.create(this.grouhKhodro));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IGrouhKhodro>>) {
        result.subscribe((res: HttpResponse<IGrouhKhodro>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
