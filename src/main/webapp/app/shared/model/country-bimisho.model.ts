export interface ICountryBimisho {
    id?: number;
    name?: string;
}

export class CountryBimisho implements ICountryBimisho {
    constructor(public id?: number, public name?: string) {}
}
