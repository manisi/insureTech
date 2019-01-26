export interface IKhesaratSrneshin {
    id?: number;
    nerkhKhesarat?: number;
    faal?: boolean;
    noeSabegheName?: string;
    noeSabegheId?: number;
    sabegheName?: string;
    sabegheId?: number;
}

export class KhesaratSrneshin implements IKhesaratSrneshin {
    constructor(
        public id?: number,
        public nerkhKhesarat?: number,
        public faal?: boolean,
        public noeSabegheName?: string,
        public noeSabegheId?: number,
        public sabegheName?: string,
        public sabegheId?: number
    ) {
        this.faal = this.faal || false;
    }
}
