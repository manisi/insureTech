import { Moment } from 'moment';

export interface IPooshesh {
    id?: number;
    name?: string;
    faal?: boolean;
    aztarikh?: Moment;
    nerkhId?: number;
}

export class Pooshesh implements IPooshesh {
    constructor(public id?: number, public name?: string, public faal?: boolean, public aztarikh?: Moment, public nerkhId?: number) {
        this.faal = this.faal || false;
    }
}
