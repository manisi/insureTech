export interface IAnvaeKhodro {
    id?: number;
    grouhVasile?: string;
    systemVasile?: string;
    onvan?: string;
    tonazh?: string;
    tedadSarneshin?: string;
    tedadSilandre?: string;
    dasteBandi?: string;
    savariType?: string;
    grouhKhodroCode?: string;
    grouhKhodroId?: number;
}

export class AnvaeKhodro implements IAnvaeKhodro {
    constructor(
        public id?: number,
        public grouhVasile?: string,
        public systemVasile?: string,
        public onvan?: string,
        public tonazh?: string,
        public tedadSarneshin?: string,
        public tedadSilandre?: string,
        public dasteBandi?: string,
        public savariType?: string,
        public grouhKhodroCode?: string,
        public grouhKhodroId?: number
    ) {}
}
