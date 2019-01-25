import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';
import { NoeSabegheService } from './noe-sabeghe.service';

@Component({
    selector: 'jhi-noe-sabeghe-update',
    templateUrl: './noe-sabeghe-update.component.html'
})
export class NoeSabegheUpdateComponent implements OnInit {
    noeSabeghe: INoeSabeghe;
    isSaving: boolean;

    constructor(protected noeSabegheService: NoeSabegheService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ noeSabeghe }) => {
            this.noeSabeghe = noeSabeghe;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.noeSabeghe.id !== undefined) {
            this.subscribeToSaveResponse(this.noeSabegheService.update(this.noeSabeghe));
        } else {
            this.subscribeToSaveResponse(this.noeSabegheService.create(this.noeSabeghe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INoeSabeghe>>) {
        result.subscribe((res: HttpResponse<INoeSabeghe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
