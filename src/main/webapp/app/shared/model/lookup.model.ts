export const enum LookupEnum {
    SALES = 'SALES',
    SARNESHIN = 'SARNESHIN'
}

export interface ILookup {
    key?: string;
    // noe?: SalesSarneshinEnum;
    value?: string;
}

export class Lookup implements ILookup {
    constructor(public key?: string, public value?: string) {}
}
