import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';
import { SabegheKhesaratService } from './sabeghe-khesarat.service';

@Component({
    selector: 'jhi-sabeghe-khesarat-update',
    templateUrl: './sabeghe-khesarat-update.component.html'
})
export class SabegheKhesaratUpdateComponent implements OnInit {
    sabegheKhesarat: ISabegheKhesarat;
    isSaving: boolean;

    constructor(protected sabegheKhesaratService: SabegheKhesaratService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sabegheKhesarat }) => {
            this.sabegheKhesarat = sabegheKhesarat;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sabegheKhesarat.id !== undefined) {
            this.subscribeToSaveResponse(this.sabegheKhesaratService.update(this.sabegheKhesarat));
        } else {
            this.subscribeToSaveResponse(this.sabegheKhesaratService.create(this.sabegheKhesarat));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISabegheKhesarat>>) {
        result.subscribe((res: HttpResponse<ISabegheKhesarat>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
