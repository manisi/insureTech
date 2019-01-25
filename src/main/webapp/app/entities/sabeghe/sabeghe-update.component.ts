import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from './sabeghe.service';

@Component({
    selector: 'jhi-sabeghe-update',
    templateUrl: './sabeghe-update.component.html'
})
export class SabegheUpdateComponent implements OnInit {
    sabeghe: ISabeghe;
    isSaving: boolean;

    constructor(protected sabegheService: SabegheService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sabeghe }) => {
            this.sabeghe = sabeghe;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sabeghe.id !== undefined) {
            this.subscribeToSaveResponse(this.sabegheService.update(this.sabeghe));
        } else {
            this.subscribeToSaveResponse(this.sabegheService.create(this.sabeghe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISabeghe>>) {
        result.subscribe((res: HttpResponse<ISabeghe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
