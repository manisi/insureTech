import { Injectable } from '@angular/core';
import { NgbDateAdapter, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Moment } from 'jalali-moment';
import * as moment from 'jalali-moment';

moment.locale('fa', { useGregorianParser: true });
// let todayJalali = moment().locale('fa').format('YYYY/M/D');

@Injectable()
export class NgbDateMomentAdapter extends NgbDateAdapter<Moment> {
    fromModel(date: Moment): NgbDateStruct {
        if (date != null && moment.isMoment(date) && date.isValid()) {
            return { year: date.year(), month: date.month() + 1, day: date.date() };
        }
        return null;
    }
    toModel(date: NgbDateStruct): Moment {
        return date ? moment(date.year + '-' + date.month + '-' + date.day, 'YYYY-MM-DD') : null;
    }
}
