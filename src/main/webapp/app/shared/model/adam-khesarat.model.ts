export interface IAdamKhesarat {
    id?: number;
    sales?: number;
    mazad?: number;
    faal?: boolean;
    sabegheName?: string;
    sabegheId?: number;
    noeSabegheName?: string;
    noeSabegheId?: number;
}

export class AdamKhesarat implements IAdamKhesarat {
    constructor(
        public id?: number,
        public sales?: number,
        public mazad?: number,
        public faal?: boolean,
        public sabegheName?: string,
        public sabegheId?: number,
        public noeSabegheName?: string,
        public noeSabegheId?: number
    ) {
        this.faal = this.faal || false;
    }
}
