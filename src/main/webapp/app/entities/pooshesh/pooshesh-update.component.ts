import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPooshesh } from 'app/shared/model/pooshesh.model';
import { PoosheshService } from './pooshesh.service';
import { INerkh } from 'app/shared/model/nerkh.model';
import { NerkhService } from 'app/entities/nerkh';

@Component({
    selector: 'insutech-pooshesh-update',
    templateUrl: './pooshesh-update.component.html'
})
export class PoosheshUpdateComponent implements OnInit {
    pooshesh: IPooshesh;
    isSaving: boolean;

    nerkhs: INerkh[];
    aztarikh: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected poosheshService: PoosheshService,
        protected nerkhService: NerkhService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pooshesh }) => {
            this.pooshesh = pooshesh;
            this.aztarikh = this.pooshesh.aztarikh != null ? this.pooshesh.aztarikh.format(DATE_TIME_FORMAT) : null;
        });
        this.nerkhService.query().subscribe(
            (res: HttpResponse<INerkh[]>) => {
                this.nerkhs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.pooshesh.aztarikh = this.aztarikh != null ? moment(this.aztarikh, DATE_TIME_FORMAT) : null;
        if (this.pooshesh.id !== undefined) {
            this.subscribeToSaveResponse(this.poosheshService.update(this.pooshesh));
        } else {
            this.subscribeToSaveResponse(this.poosheshService.create(this.pooshesh));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPooshesh>>) {
        result.subscribe((res: HttpResponse<IPooshesh>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackNerkhById(index: number, item: INerkh) {
        return item.id;
    }
}
