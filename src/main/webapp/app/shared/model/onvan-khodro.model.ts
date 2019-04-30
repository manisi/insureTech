import { Moment } from 'jalali-moment';

export interface IOnvanKhodro {
    id?: number;
    name?: string;
    sharh?: string;
    azTarikh?: Moment;
    taTarikh?: Moment;
    faal?: boolean;
}

export class OnvanKhodro implements IOnvanKhodro {
    constructor(
        public id?: number,
        public name?: string,
        public sharh?: string,
        public azTarikh?: Moment,
        public taTarikh?: Moment,
        public faal?: boolean
    ) {
        this.faal = this.faal || false;
    }
}
