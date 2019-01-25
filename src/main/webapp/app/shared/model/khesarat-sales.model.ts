export const enum SalesSarneshinEnum {
    SALES = 'SALES',
    SARNESHIN = 'SARNESHIN'
}

export interface IKhesaratSales {
    id?: number;
    noe?: SalesSarneshinEnum;
    nerkhKhesarat?: number;
    faal?: boolean;
    sabegheName?: string;
    sabegheId?: number;
    noeSabegheName?: string;
    noeSabegheId?: number;
}

export class KhesaratSales implements IKhesaratSales {
    constructor(
        public id?: number,
        public noe?: SalesSarneshinEnum,
        public nerkhKhesarat?: number,
        public faal?: boolean,
        public sabegheName?: string,
        public sabegheId?: number,
        public noeSabegheName?: string,
        public noeSabegheId?: number
    ) {
        this.faal = this.faal || false;
    }
}
