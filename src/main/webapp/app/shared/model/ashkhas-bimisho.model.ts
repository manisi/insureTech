import { Moment } from 'moment';

export const enum NoeShakhs {
    HAGHIGHI = 'HAGHIGHI',
    HOGHOOGHI = 'HOGHOOGHI',
    ATBAE = 'ATBAE'
}

export interface IAshkhasBimisho {
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    hireDate?: Moment;
    noeShakhs?: NoeShakhs;
}

export class AshkhasBimisho implements IAshkhasBimisho {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public phoneNumber?: string,
        public hireDate?: Moment,
        public noeShakhs?: NoeShakhs
    ) {}
}
