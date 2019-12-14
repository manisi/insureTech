import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IVaziatBime } from 'app/shared/model/vaziat-bime.model';
import { VaziatBimeService } from './vaziat-bime.service';

@Component({
    selector: 'jhi-vaziat-bime-update',
    templateUrl: './vaziat-bime-update.component.html'
})
export class VaziatBimeUpdateComponent implements OnInit {
    vaziatBime: IVaziatBime;
    isSaving: boolean;

    constructor(protected vaziatBimeService: VaziatBimeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vaziatBime }) => {
            this.vaziatBime = vaziatBime;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.vaziatBime.id !== undefined) {
            this.subscribeToSaveResponse(this.vaziatBimeService.update(this.vaziatBime));
        } else {
            this.subscribeToSaveResponse(this.vaziatBimeService.create(this.vaziatBime));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVaziatBime>>) {
        result.subscribe((res: HttpResponse<IVaziatBime>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
