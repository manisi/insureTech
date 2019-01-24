export interface IGrouhKhodro {
    id?: number;
    name?: string;
    faal?: boolean;
}

export class GrouhKhodro implements IGrouhKhodro {
    constructor(public id?: number, public name?: string, public faal?: boolean) {
        this.faal = this.faal || false;
    }
}
