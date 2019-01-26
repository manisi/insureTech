export interface IAdamKhesaratSarneshin {
    id?: number;
    sarneshin?: number;
    faal?: boolean;
    noeSabegheName?: string;
    noeSabegheId?: number;
    sabegheName?: string;
    sabegheId?: number;
}

export class AdamKhesaratSarneshin implements IAdamKhesaratSarneshin {
    constructor(
        public id?: number,
        public sarneshin?: number,
        public faal?: boolean,
        public noeSabegheName?: string,
        public noeSabegheId?: number,
        public sabegheName?: string,
        public sabegheId?: number
    ) {
        this.faal = this.faal || false;
    }
}
