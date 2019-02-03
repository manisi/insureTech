export interface IKohnegi {
    id?: number;
    darsadHarSal?: number;
    maxDarsad?: number;
    sharh?: any;
    faal?: boolean;
    grouhKhodroCode?: string;
    grouhKhodroId?: number;
}

export class Kohnegi implements IKohnegi {
    constructor(
        public id?: number,
        public darsadHarSal?: number,
        public maxDarsad?: number,
        public sharh?: any,
        public faal?: boolean,
        public grouhKhodroCode?: string,
        public grouhKhodroId?: number
    ) {
        this.faal = this.faal || false;
    }
}
