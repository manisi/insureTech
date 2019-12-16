import { Moment } from 'jalali-moment';

export interface ITakhfifTavafoghi {
    id?: number;
    name?: string;
    darsadTakhfif?: number;
    azTarikh?: Moment;
    faal?: boolean;
    sherkatBimeName?: string;
    sherkatBimeId?: number;
}

export class TakhfifTavafoghi implements ITakhfifTavafoghi {
    constructor(
        public id?: number,
        public name?: string,
        public darsadTakhfif?: number,
        public azTarikh?: Moment,
        public faal?: boolean,
        public sherkatBimeName?: string,
        public sherkatBimeId?: number
    ) {
        this.faal = this.faal || false;
    }
}
