import { IPoosheshBimisho } from 'app/shared/model//pooshesh-bimisho.model';
import { ISherkatBimeBimisho } from 'app/shared/model//sherkat-bime-bimisho.model';

export interface INerkhBimisho {
    id?: number;
    name?: string;
    faal?: boolean;
    mablagh?: number;
    nerkhs?: IPoosheshBimisho[];
    sherkatBimes?: ISherkatBimeBimisho[];
}

export class NerkhBimisho implements INerkhBimisho {
    constructor(
        public id?: number,
        public name?: string,
        public faal?: boolean,
        public mablagh?: number,
        public nerkhs?: IPoosheshBimisho[],
        public sherkatBimes?: ISherkatBimeBimisho[]
    ) {
        this.faal = this.faal || false;
    }
}
