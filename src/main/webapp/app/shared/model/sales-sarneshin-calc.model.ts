import { Moment } from 'jalali-moment';

export interface ISalesSarneshinCalc {
    id?: number;
    mablaghSalesMazad?: number;
    tedadRooz?: number;
    mablaghHaghBime?: number;
    tarikhShoroo?: Moment;
    tarikhPayan?: Moment;
    namesherkatName?: string;
    namesherkatId?: number;
    grouhKhodroName?: string;
    grouhKhodroId?: number;
}

export class SalesSarneshinCalc implements ISalesSarneshinCalc {
    constructor(
        public id?: number,
        public mablaghSalesMazad?: number,
        public tedadRooz?: number,
        public mablaghHaghBime?: number,
        public tarikhShoroo?: Moment,
        public tarikhPayan?: Moment,
        public namesherkatName?: string,
        public namesherkatId?: number,
        public grouhKhodroName?: string,
        public grouhKhodroId?: number
    ) {}
}
