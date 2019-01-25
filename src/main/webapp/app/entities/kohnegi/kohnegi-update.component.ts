import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiDataUtils } from 'ng-jhipster';
import { IKohnegi } from 'app/shared/model/kohnegi.model';
import { KohnegiService } from './kohnegi.service';

@Component({
    selector: 'jhi-kohnegi-update',
    templateUrl: './kohnegi-update.component.html'
})
export class KohnegiUpdateComponent implements OnInit {
    kohnegi: IKohnegi;
    isSaving: boolean;

    constructor(protected dataUtils: JhiDataUtils, protected kohnegiService: KohnegiService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ kohnegi }) => {
            this.kohnegi = kohnegi;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.kohnegi.id !== undefined) {
            this.subscribeToSaveResponse(this.kohnegiService.update(this.kohnegi));
        } else {
            this.subscribeToSaveResponse(this.kohnegiService.create(this.kohnegi));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IKohnegi>>) {
        result.subscribe((res: HttpResponse<IKohnegi>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
