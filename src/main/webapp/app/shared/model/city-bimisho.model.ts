export interface ICityBimisho {
    id?: number;
    name?: string;
    tipsId?: number;
}

export class CityBimisho implements ICityBimisho {
    constructor(public id?: number, public name?: string, public tipsId?: number) {}
}
