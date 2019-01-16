export const enum NoeKhodro {
    SAVARI = 'SAVARI',
    TAXI = 'TAXI',
    AUTOCAR = 'AUTOCAR',
    CAMIOON = 'CAMIOON'
}

export interface ITipKhodroBimisho {
    id?: number;
    name?: string;
    model?: string;
    faal?: boolean;
    noe?: NoeKhodro;
    khodroId?: number;
}

export class TipKhodroBimisho implements ITipKhodroBimisho {
    constructor(
        public id?: number,
        public name?: string,
        public model?: string,
        public faal?: boolean,
        public noe?: NoeKhodro,
        public khodroId?: number
    ) {
        this.faal = this.faal || false;
    }
}
