import { IPooshesh } from 'app/shared/model/pooshesh.model';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';

export interface INerkh {
    id?: number;
    name?: string;
    faal?: boolean;
    mablagh?: number;
    nerkhs?: IPooshesh[];
    sherkatBimes?: ISherkatBime[];
}

export class Nerkh implements INerkh {
    constructor(
        public id?: number,
        public name?: string,
        public faal?: boolean,
        public mablagh?: number,
        public nerkhs?: IPooshesh[],
        public sherkatBimes?: ISherkatBime[]
    ) {
        this.faal = this.faal || false;
    }
}
