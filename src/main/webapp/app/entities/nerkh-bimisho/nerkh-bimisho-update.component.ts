import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';
import { NerkhBimishoService } from './nerkh-bimisho.service';
import { ISherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';
import { SherkatBimeBimishoService } from 'app/entities/sherkat-bime-bimisho';

@Component({
    selector: 'insutech-nerkh-bimisho-update',
    templateUrl: './nerkh-bimisho-update.component.html'
})
export class NerkhBimishoUpdateComponent implements OnInit {
    nerkh: INerkhBimisho;
    isSaving: boolean;

    sherkatbimes: ISherkatBimeBimisho[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected nerkhService: NerkhBimishoService,
        protected sherkatBimeService: SherkatBimeBimishoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nerkh }) => {
            this.nerkh = nerkh;
        });
        this.sherkatBimeService.query().subscribe(
            (res: HttpResponse<ISherkatBimeBimisho[]>) => {
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

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INerkhBimisho>>) {
        result.subscribe((res: HttpResponse<INerkhBimisho>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSherkatBimeById(index: number, item: ISherkatBimeBimisho) {
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
