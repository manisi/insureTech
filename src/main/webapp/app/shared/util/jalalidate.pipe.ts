import { Pipe, PipeTransform } from '@angular/core';
// import moment = require('jalali-moment');
import * as moment from 'jalali-moment';

@Pipe({ name: 'jalali' })
export class JalaliPipe implements PipeTransform {
    transform(value: any, args?: any): any {
        const MomentDate = moment(value).locale('en');
        return MomentDate.locale('fa').format('YYYY/MM/DD');
    }
}
