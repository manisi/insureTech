import { Pipe, PipeTransform } from '@angular/core';
import moment = require('jalali-moment');

@Pipe({ name: 'jalali' })
export class JalaliPipe implements PipeTransform {
    transform(value: any, args?: any): any {
        // moment.locale('en');
        const MomentDate = moment(value);
        MomentDate.locale('en');
        return MomentDate.locale('fa').format('YYYY/MM/DD');
    }
}
