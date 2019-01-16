import { ITipKhodroBimisho } from 'app/shared/model//tip-khodro-bimisho.model';

export const enum NoeKhodro {
    SAVARI = 'SAVARI',
    TAXI = 'TAXI',
    AUTOCAR = 'AUTOCAR',
    CAMIOON = 'CAMIOON'
}

export interface IKhodroBimisho {
    id?: number;
    name?: string;
    model?: string;
    faal?: boolean;
    noe?: NoeKhodro;
    tips?: ITipKhodroBimisho[];
}

export class KhodroBimisho implements IKhodroBimisho {
    constructor(
        public id?: number,
        public name?: string,
        public model?: string,
        public faal?: boolean,
        public noe?: NoeKhodro,
        public tips?: ITipKhodroBimisho[]
    ) {
        this.faal = this.faal || false;
    }
}
