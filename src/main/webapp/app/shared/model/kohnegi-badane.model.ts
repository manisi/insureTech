export interface IKohnegiBadane {
    id?: number;
    darsadHarSal?: number;
    maxDarsad?: number;
    sharh?: any;
    faal?: boolean;
    grouhKhodroCode?: string;
    grouhKhodroName?: string;
    grouhKhodroId?: number;
}

export class KohnegiBadane implements IKohnegiBadane {
    constructor(
        public id?: number,
        public darsadHarSal?: number,
        public maxDarsad?: number,
        public sharh?: any,
        public faal?: boolean,
        public grouhKhodroCode?: string,
        public grouhKhodroName?: string,
        public grouhKhodroId?: number
    ) {
        this.faal = this.faal || false;
    }
}
