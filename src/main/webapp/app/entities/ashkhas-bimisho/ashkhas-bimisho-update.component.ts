import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAshkhasBimisho } from 'app/shared/model/ashkhas-bimisho.model';
import { AshkhasBimishoService } from './ashkhas-bimisho.service';

@Component({
    selector: 'insutech-ashkhas-bimisho-update',
    templateUrl: './ashkhas-bimisho-update.component.html'
})
export class AshkhasBimishoUpdateComponent implements OnInit {
    ashkhas: IAshkhasBimisho;
    isSaving: boolean;
    hireDate: string;

    constructor(protected ashkhasService: AshkhasBimishoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ashkhas }) => {
            this.ashkhas = ashkhas;
            this.hireDate = this.ashkhas.hireDate != null ? this.ashkhas.hireDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.ashkhas.hireDate = this.hireDate != null ? moment(this.hireDate, DATE_TIME_FORMAT) : null;
        if (this.ashkhas.id !== undefined) {
            this.subscribeToSaveResponse(this.ashkhasService.update(this.ashkhas));
        } else {
            this.subscribeToSaveResponse(this.ashkhasService.create(this.ashkhas));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAshkhasBimisho>>) {
        result.subscribe((res: HttpResponse<IAshkhasBimisho>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
