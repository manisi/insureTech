import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IKohnegiBadane } from 'app/shared/model/kohnegi-badane.model';

@Component({
    selector: 'jhi-kohnegi-badane-detail',
    templateUrl: './kohnegi-badane-detail.component.html'
})
export class KohnegiBadaneDetailComponent implements OnInit {
    kohnegiBadane: IKohnegiBadane;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kohnegiBadane }) => {
            this.kohnegiBadane = kohnegiBadane;
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
