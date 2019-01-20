import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INerkh } from 'app/shared/model/nerkh.model';
import { NerkhService } from './nerkh.service';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from 'app/entities/sherkat-bime';

@Component({
    selector: 'insutech-nerkh-update',
    templateUrl: './nerkh-update.component.html'
})
export class NerkhUpdateComponent implements OnInit {
    nerkh: INerkh;
    isSaving: boolean;

    sherkatbimes: ISherkatBime[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected nerkhService: NerkhService,
        protected sherkatBimeService: SherkatBimeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nerkh }) => {
            this.nerkh = nerkh;
        });
        this.sherkatBimeService.query().subscribe(
            (res: HttpResponse<ISherkatBime[]>) => {
                this.sherkatbimes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.nerkh.id !== undefined) {
            this.subscribeToSaveResponse(this.nerkhService.update(this.nerkh));
        } else {
            this.subscribeToSaveResponse(this.nerkhService.create(this.nerkh));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INerkh>>) {
        result.subscribe((res: HttpResponse<INerkh>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSherkatBimeById(index: number, item: ISherkatBime) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
