export interface IAdamKhesaratBadane {
    id?: number;
    badane?: number;
    faal?: boolean;
    noeSabegheName?: string;
    noeSabegheId?: number;
    sabegheName?: string;
    sabegheId?: number;
}

export class AdamKhesaratBadane implements IAdamKhesaratBadane {
    constructor(
        public id?: number,
        public badane?: number,
        public faal?: boolean,
        public noeSabegheName?: string,
        public noeSabegheId?: number,
        public sabegheName?: string,
        public sabegheId?: number
    ) {
        this.faal = this.faal || false;
    }
}
