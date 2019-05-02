export interface IKohnegi {
    id?: number;
    darsadHarSal?: number;
    maxDarsad?: number;
    sharh?: any;
    faal?: boolean;
    grouhKhodroCode?: string;
    grouhKhodroName?: string;
    grouhKhodroId?: number;
    sherkatBimeName?: string;
    sherkatBimeId?: number;
}

export class Kohnegi implements IKohnegi {
    constructor(
        public id?: number,
        public darsadHarSal?: number,
        public maxDarsad?: number,
        public sharh?: any,
        public faal?: boolean,
        public grouhKhodroCode?: string,
        public grouhKhodroName?: string,
        public grouhKhodroId?: number,
        public sherkatBimeName?: string,
        public sherkatBimeId?: number
    ) {
        this.faal = this.faal || false;
    }
}
