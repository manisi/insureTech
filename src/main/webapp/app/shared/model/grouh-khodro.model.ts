export interface IGrouhKhodro {
    id?: number;
    name?: string;
    faal?: boolean;
    code?: number;
}

export class GrouhKhodro implements IGrouhKhodro {
    constructor(public id?: number, public name?: string, public faal?: boolean, public code?: number) {
        this.faal = this.faal || false;
    }
}
