import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiDataUtils } from 'ng-jhipster';
import { IGroupOperationsData } from 'app/shared/model/group-operations-data.model';
import { GroupOperationsDataService } from './group-operations-data.service';

@Component({
    selector: 'jhi-group-operations-data-update',
    templateUrl: './group-operations-data-update.component.html'
})
export class GroupOperationsDataUpdateComponent implements OnInit {
    groupOperationsData: IGroupOperationsData;
    isSaving: boolean;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected groupOperationsDataService: GroupOperationsDataService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ groupOperationsData }) => {
            this.groupOperationsData = groupOperationsData;
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
        if (this.groupOperationsData.id !== undefined) {
            this.subscribeToSaveResponse(this.groupOperationsDataService.update(this.groupOperationsData));
        } else {
            this.subscribeToSaveResponse(this.groupOperationsDataService.create(this.groupOperationsData));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupOperationsData>>) {
        result.subscribe((res: HttpResponse<IGroupOperationsData>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
