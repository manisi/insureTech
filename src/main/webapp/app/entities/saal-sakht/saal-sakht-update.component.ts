import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISaalSakht } from 'app/shared/model/saal-sakht.model';
import { SaalSakhtService } from './saal-sakht.service';

@Component({
    selector: 'jhi-saal-sakht-update',
    templateUrl: './saal-sakht-update.component.html'
})
export class SaalSakhtUpdateComponent implements OnInit {
    saalSakht: ISaalSakht;
    isSaving: boolean;

    constructor(protected saalSakhtService: SaalSakhtService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ saalSakht }) => {
            this.saalSakht = saalSakht;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.saalSakht.id !== undefined) {
            this.subscribeToSaveResponse(this.saalSakhtService.update(this.saalSakht));
        } else {
            this.subscribeToSaveResponse(this.saalSakhtService.create(this.saalSakht));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISaalSakht>>) {
        result.subscribe((res: HttpResponse<ISaalSakht>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
