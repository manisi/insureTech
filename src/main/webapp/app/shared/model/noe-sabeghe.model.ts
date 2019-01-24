export interface INoeSabeghe {
    id?: number;
    name?: string;
    sharh?: string;
    faal?: boolean;
}

export class NoeSabeghe implements INoeSabeghe {
    constructor(public id?: number, public name?: string, public sharh?: string, public faal?: boolean) {
        this.faal = this.faal || false;
    }
}
