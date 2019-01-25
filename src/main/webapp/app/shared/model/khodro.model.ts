import { ITipKhodro } from 'app/shared/model//tip-khodro.model';

export const enum NoeKhodro {
    SAVARI = 'SAVARI',
    TAXI = 'TAXI',
    AUTOCAR = 'AUTOCAR',
    CAMIOON = 'CAMIOON'
}

export interface IKhodro {
    id?: number;
    name?: string;
    model?: string;
    faal?: boolean;
    noe?: NoeKhodro;
    tips?: ITipKhodro[];
}

export class Khodro implements IKhodro {
    constructor(
        public id?: number,
        public name?: string,
        public model?: string,
        public faal?: boolean,
        public noe?: NoeKhodro,
        public tips?: ITipKhodro[]
    ) {
        this.faal = this.faal || false;
    }
}
