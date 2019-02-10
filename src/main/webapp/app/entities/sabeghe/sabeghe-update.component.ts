import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from './sabeghe.service';
import { NgbDateStruct, NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import * as moment from 'jalali-moment';
//import {toGregorian} from "@ng-bootstrap/ng-bootstrap/datepicker/jalali/jalali";
//moment.locale('fa', { useGregorianParser: true });
@Component({
    selector: 'jhi-sabeghe-update',
    templateUrl: './sabeghe-update.component.html'
})
export class SabegheUpdateComponent implements OnInit {
    sabeghe: ISabeghe;
    isSaving: boolean;
    model: NgbDateStruct;
    date: { year: number; month: number; day: number };

    constructor(protected sabegheService: SabegheService, protected activatedRoute: ActivatedRoute, private calendar: NgbCalendar) {
        // this.dayTemplateData = this.dayTemplateData.bind(this);
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sabeghe }) => {
            this.sabeghe = sabeghe;
        });
    }

    selectToday() {
        this.model = this.calendar.getToday();
        //   this.date = new NgbDate(this.calendar.getToday().year, this.calendar.getToday().month, this.calendar.getToday().day);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sabeghe.id !== undefined) {
            this.subscribeToSaveResponse(this.sabegheService.update(this.sabeghe));
        } else {
            this.subscribeToSaveResponse(this.sabegheService.create(this.sabeghe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISabeghe>>) {
        result.subscribe((res: HttpResponse<ISabeghe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
