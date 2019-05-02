export interface IJarimeDirkard {
    id?: number;
    roozane?: number;
    faal?: boolean;
    grouhKhodroName?: string;
    grouhKhodroId?: number;
    sherkatBimeName?: string;
    sherkatBimeId?: number;
}

export class JarimeDirkard implements IJarimeDirkard {
    constructor(
        public id?: number,
        public roozane?: number,
        public faal?: boolean,
        public grouhKhodroName?: string,
        public grouhKhodroId?: number,
        public sherkatBimeName?: string,
        public sherkatBimeId?: number
    ) {
        this.faal = this.faal || false;
    }
}
