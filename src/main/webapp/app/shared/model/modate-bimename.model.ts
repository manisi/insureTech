export interface IModateBimename {
    id?: number;
    name?: string;
    sharh?: string;
    faal?: boolean;
}

export class ModateBimename implements IModateBimename {
    constructor(public id?: number, public name?: string, public sharh?: string, public faal?: boolean) {
        this.faal = this.faal || false;
    }
}
