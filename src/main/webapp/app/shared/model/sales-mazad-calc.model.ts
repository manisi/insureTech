import { Moment } from 'jalali-moment';

export interface ISalesMazadCalc {
    id?: number;
    mablaghSalesMazad?: number;
    tedadRooz?: number;
    haghBime?: number;
    tarikhShoroo?: Moment;
    tarikhPayan?: Moment;
    namesherkatName?: string;
    namesherkatId?: number;
    grouhKhodroName?: string;
    grouhKhodroId?: number;
}

export class SalesMazadCalc implements ISalesMazadCalc {
    constructor(
        public id?: number,
        public mablaghSalesMazad?: number,
        public tedadRooz?: number,
        public haghBime?: number,
        public tarikhShoroo?: Moment,
        public tarikhPayan?: Moment,
        public namesherkatName?: string,
        public namesherkatId?: number,
        public grouhKhodroName?: string,
        public grouhKhodroId?: number
    ) {}
}
