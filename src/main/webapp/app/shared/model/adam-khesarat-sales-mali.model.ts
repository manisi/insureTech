export interface IAdamKhesaratSalesMali {
    id?: number;
    salesMali?: number;
    faal?: boolean;
    sabegheName?: string;
    sabegheId?: number;
    noeSabegheName?: string;
    noeSabegheId?: number;
}

export class AdamKhesaratSalesMali implements IAdamKhesaratSalesMali {
    constructor(
        public id?: number,
        public salesMali?: number,
        public faal?: boolean,
        public sabegheName?: string,
        public sabegheId?: number,
        public noeSabegheName?: string,
        public noeSabegheId?: number
    ) {
        this.faal = this.faal || false;
    }
}
