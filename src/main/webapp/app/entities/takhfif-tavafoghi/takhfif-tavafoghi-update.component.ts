import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { ITakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';
import { TakhfifTavafoghiService } from './takhfif-tavafoghi.service';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from 'app/entities/sherkat-bime';

@Component({
    selector: 'jhi-takhfif-tavafoghi-update',
    templateUrl: './takhfif-tavafoghi-update.component.html'
})
export class TakhfifTavafoghiUpdateComponent implements OnInit {
    takhfifTavafoghi: ITakhfifTavafoghi;
    isSaving: boolean;

    sherkatbimes: ISherkatBime[];
    azTarikhDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected takhfifTavafoghiService: TakhfifTavafoghiService,
        protected sherkatBimeService: SherkatBimeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ takhfifTavafoghi }) => {
            this.takhfifTavafoghi = takhfifTavafoghi;
        });
        this.sherkatBimeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISherkatBime[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISherkatBime[]>) => response.body)
            )
            .subscribe((res: ISherkatBime[]) => (this.sherkatbimes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.takhfifTavafoghi.id !== undefined) {
            this.subscribeToSaveResponse(this.takhfifTavafoghiService.update(this.takhfifTavafoghi));
        } else {
            this.subscribeToSaveResponse(this.takhfifTavafoghiService.create(this.takhfifTavafoghi));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITakhfifTavafoghi>>) {
        result.subscribe((res: HttpResponse<ITakhfifTavafoghi>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
