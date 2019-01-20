export interface IMohasebeSales {
    id?: number;
    nameSherkat?: string;
    saltakhfif?: number;
    tipsId?: number;
}

export class MohasebeSales implements IMohasebeSales {
    constructor(public id?: number, public nameSherkat?: string, public saltakhfif?: number, public tipsId?: number) {}
}
