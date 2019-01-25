export interface IMohasebeBadane {
    id?: number;
    nameSherkat?: string;
    saltakhfif?: number;
    tipsId?: number;
}

export class MohasebeBadane implements IMohasebeBadane {
    constructor(public id?: number, public nameSherkat?: string, public saltakhfif?: number, public tipsId?: number) {}
}
