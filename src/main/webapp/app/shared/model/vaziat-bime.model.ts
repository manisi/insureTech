export interface IVaziatBime {
    id?: number;
    title?: string;
    faal?: boolean;
}

export class VaziatBime implements IVaziatBime {
    constructor(public id?: number, public title?: string, public faal?: boolean) {
        this.faal = this.faal || false;
    }
}
