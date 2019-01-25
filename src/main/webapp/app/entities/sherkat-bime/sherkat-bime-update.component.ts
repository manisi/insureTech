import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from './sherkat-bime.service';
import { INerkh } from 'app/shared/model/nerkh.model';
import { NerkhService } from 'app/entities/nerkh';

@Component({
    selector: 'insutech-sherkat-bime-update',
    templateUrl: './sherkat-bime-update.component.html'
})
export class SherkatBimeUpdateComponent implements OnInit {
    sherkatBime: ISherkatBime;
    isSaving: boolean;

    nerkhs: INerkh[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sherkatBimeService: SherkatBimeService,
        protected nerkhService: NerkhService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sherkatBime }) => {
            this.sherkatBime = sherkatBime;
        });
        this.nerkhService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<INerkh[]>) => mayBeOk.ok),
                map((response: HttpResponse<INerkh[]>) => response.body)
            )
            .subscribe((res: INerkh[]) => (this.nerkhs = res), (res: HttpErrorResponse) => this.onError(res.message));
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

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISherkatBime>>) {
        result.subscribe((res: HttpResponse<ISherkatBime>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
