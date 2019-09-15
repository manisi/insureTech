import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IGroupOperationsData } from 'app/shared/model/group-operations-data.model';

@Component({
    selector: 'jhi-group-operations-data-detail',
    templateUrl: './group-operations-data-detail.component.html'
})
export class GroupOperationsDataDetailComponent implements OnInit {
    groupOperationsData: IGroupOperationsData;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
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
    previousState() {
        window.history.back();
    }
}
