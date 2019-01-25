import { INerkh } from 'app/shared/model/nerkh.model';

export interface ISherkatBime {
    id?: number;
    name?: string;
    faal?: boolean;
    names?: INerkh[];
}

export class SherkatBime implements ISherkatBime {
    constructor(public id?: number, public name?: string, public faal?: boolean, public names?: INerkh[]) {
        this.faal = this.faal || false;
    }
}
