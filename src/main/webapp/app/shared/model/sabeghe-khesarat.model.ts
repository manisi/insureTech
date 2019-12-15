export interface ISabegheKhesarat {
    id?: number;
    name?: string;
    sharh?: string;
    faal?: boolean;
}

export class SabegheKhesarat implements ISabegheKhesarat {
    constructor(public id?: number, public name?: string, public sharh?: string, public faal?: boolean) {
        this.faal = this.faal || false;
    }
}
