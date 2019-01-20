import { Moment } from 'moment';

export const enum NoeShakhs {
    HAGHIGHI = 'HAGHIGHI',
    HOGHOOGHI = 'HOGHOOGHI',
    ATBAE = 'ATBAE'
}

export interface IAshkhas {
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    phoneNumber?: string;
    hireDate?: Moment;
    noeShakhs?: NoeShakhs;
}

export class Ashkhas implements IAshkhas {
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
