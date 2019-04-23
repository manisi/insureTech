import { Moment } from 'moment';

export interface IMoredEstefadeSales {
    id?: number;
    darsadEzafeNerkh?: number;
    azTarikh?: Moment;
    taTarikh?: Moment;
    faal?: boolean;
    grouhKhodroName?: string;
    grouhKhodroId?: number;
    sherkatBimeName?: string;
    sherkatBimeId?: number;
}

export class MoredEstefadeSales implements IMoredEstefadeSales {
    constructor(
        public id?: number,
        public darsadEzafeNerkh?: number,
        public azTarikh?: Moment,
        public taTarikh?: Moment,
        public faal?: boolean,
        public grouhKhodroName?: string,
        public grouhKhodroId?: number,
        public sherkatBimeName?: string,
        public sherkatBimeId?: number
    ) {
        this.faal = this.faal || false;
    }
}
