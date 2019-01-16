import { INerkhBimisho } from 'app/shared/model//nerkh-bimisho.model';

export interface ISherkatBimeBimisho {
    id?: number;
    name?: string;
    faal?: boolean;
    names?: INerkhBimisho[];
}

export class SherkatBimeBimisho implements ISherkatBimeBimisho {
    constructor(public id?: number, public name?: string, public faal?: boolean, public names?: INerkhBimisho[]) {
        this.faal = this.faal || false;
    }
}
