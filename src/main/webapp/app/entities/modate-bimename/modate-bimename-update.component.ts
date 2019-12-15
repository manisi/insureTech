import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IModateBimename } from 'app/shared/model/modate-bimename.model';
import { ModateBimenameService } from './modate-bimename.service';

@Component({
    selector: 'jhi-modate-bimename-update',
    templateUrl: './modate-bimename-update.component.html'
})
export class ModateBimenameUpdateComponent implements OnInit {
    modateBimename: IModateBimename;
    isSaving: boolean;

    constructor(protected modateBimenameService: ModateBimenameService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ modateBimename }) => {
            this.modateBimename = modateBimename;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.modateBimename.id !== undefined) {
            this.subscribeToSaveResponse(this.modateBimenameService.update(this.modateBimename));
        } else {
            this.subscribeToSaveResponse(this.modateBimenameService.create(this.modateBimename));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IModateBimename>>) {
        result.subscribe((res: HttpResponse<IModateBimename>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
