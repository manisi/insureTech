export const enum SalesSarneshinEnum {
    SALES = 'SALES',
    SARNESHIN = 'SARNESHIN'
}

export interface IKhesaratSalesMali {
    id?: number;
    noe?: SalesSarneshinEnum;
    nerkhKhesaratMali?: number;
    faal?: boolean;
    sabegheName?: string;
    sabegheId?: number;
    noeSabegheName?: string;
    noeSabegheId?: number;
}

export class KhesaratSalesMali implements IKhesaratSalesMali {
    constructor(
        public id?: number,
        public noe?: SalesSarneshinEnum,
        public nerkhKhesaratMali?: number,
        public faal?: boolean,
        public sabegheName?: string,
        public sabegheId?: number,
        public noeSabegheName?: string,
        public noeSabegheId?: number
    ) {
        this.faal = this.faal || false;
    }
}
