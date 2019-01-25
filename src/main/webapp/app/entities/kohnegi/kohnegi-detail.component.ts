import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IKohnegi } from 'app/shared/model/kohnegi.model';

@Component({
    selector: 'jhi-kohnegi-detail',
    templateUrl: './kohnegi-detail.component.html'
})
export class KohnegiDetailComponent implements OnInit {
    kohnegi: IKohnegi;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
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
    previousState() {
        window.history.back();
    }
}
