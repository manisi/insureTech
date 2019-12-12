export interface ISaalSakht {
    id?: number;
    saalShamsi?: string;
    saalMiladi?: string;
}

export class SaalSakht implements ISaalSakht {
    constructor(public id?: number, public saalShamsi?: string, public saalMiladi?: string) {}
}
