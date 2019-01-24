export interface ISabeghe {
    id?: number;
    name?: string;
    sharh?: string;
    faal?: boolean;
}

export class Sabeghe implements ISabeghe {
    constructor(public id?: number, public name?: string, public sharh?: string, public faal?: boolean) {
        this.faal = this.faal || false;
    }
}
