export interface ICity {
    id?: number;
    name?: string;
    tipsId?: number;
}

export class City implements ICity {
    constructor(public id?: number, public name?: string, public tipsId?: number) {}
}
