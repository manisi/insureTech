import { Moment } from 'jalali-moment';

export interface ISabeghe {
    id?: number;
    name?: string;
    sharh?: string;
    faal?: boolean;
    tarikh?: Moment;
}

export class Sabeghe implements ISabeghe {
    constructor(public id?: number, public name?: string, public sharh?: string, public faal?: boolean, public tarikh?: Moment) {
        this.faal = this.faal || false;
    }
}
