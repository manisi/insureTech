export interface IKohnegiBadane {
    id?: number;
    darsadHarSal?: number;
    maxDarsad?: number;
    sharh?: any;
    faal?: boolean;
}

export class KohnegiBadane implements IKohnegiBadane {
    constructor(public id?: number, public darsadHarSal?: number, public maxDarsad?: number, public sharh?: any, public faal?: boolean) {
        this.faal = this.faal || false;
    }
}
