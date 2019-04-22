import { Moment } from 'moment';

export interface ISalesJaniCalc {
    id?: number;
    mablaghJani?: number;
    mablaghMaliEjbari?: number;
    tedadRooz?: number;
    tarikhShoroFaaliat?: Moment;
    tarikhPayanFaaliat?: Moment;
    naamSherkat?: string;
    haghbime?: number;
    bimenameName?: string;
    bimenameId?: number;
    grouhKhodroName?: string;
    grouhKhodroId?: number;
}

export class SalesJaniCalc implements ISalesJaniCalc {
    constructor(
        public id?: number,
        public mablaghJani?: number,
        public mablaghMaliEjbari?: number,
        public tedadRooz?: number,
        public tarikhShoroFaaliat?: Moment,
        public tarikhPayanFaaliat?: Moment,
        public naamSherkat?: string,
        public haghbime?: number,
        public bimenameName?: string,
        public bimenameId?: number,
        public grouhKhodroName?: string,
        public grouhKhodroId?: number
    ) {}
}
