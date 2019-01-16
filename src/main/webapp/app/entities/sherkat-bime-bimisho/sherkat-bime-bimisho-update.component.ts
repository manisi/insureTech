import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';
import { SherkatBimeBimishoService } from './sherkat-bime-bimisho.service';
import { INerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';
import { NerkhBimishoService } from 'app/entities/nerkh-bimisho';

@Component({
    selector: 'insutech-sherkat-bime-bimisho-update',
    templateUrl: './sherkat-bime-bimisho-update.component.html'
})
export class SherkatBimeBimishoUpdateComponent implements OnInit {
    sherkatBime: ISherkatBimeBimisho;
    isSaving: boolean;

    nerkhs: INerkhBimisho[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sherkatBimeService: SherkatBimeBimishoService,
        protected nerkhService: NerkhBimishoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sherkatBime }) => {
            this.sherkatBime = sherkatBime;
        });
        this.nerkhService.query().subscribe(
            (res: HttpResponse<INerkhBimisho[]>) => {
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
        if (this.sherkatBime.id !== undefined) {
            this.subscribeToSaveResponse(this.sherkatBimeService.update(this.sherkatBime));
        } else {
            this.subscribeToSaveResponse(this.sherkatBimeService.create(this.sherkatBime));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISherkatBimeBimisho>>) {
        result.subscribe((res: HttpResponse<ISherkatBimeBimisho>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackNerkhById(index: number, item: INerkhBimisho) {
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
