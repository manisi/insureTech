export interface IEstelaamSalesNerkh {
    id?: number;
    anvaeKhodroOnvan?: string;
    anvaeKhodroId?: number;
    saalSakhtSaalShamsi?: string;
    saalSakhtId?: number;
    onvanKhodroName?: string;
    onvanKhodroId?: number;
    adamKhesaratSales?: string;
    adamKhesaratId?: number;
    adamKhesaratSarneshinSarneshin?: string;
    adamKhesaratSarneshinId?: number;
    khesaratSrneshinNerkhKhesarat?: string;
    khesaratSrneshinId?: number;
    khesaratSalesNerkhKhesarat?: string;
    khesaratSalesId?: number;
}

export class EstelaamSalesNerkh implements IEstelaamSalesNerkh {
    constructor(
        public id?: number,
        public anvaeKhodroOnvan?: string,
        public anvaeKhodroId?: number,
        public saalSakhtSaalShamsi?: string,
        public saalSakhtId?: number,
        public onvanKhodroName?: string,
        public onvanKhodroId?: number,
        public adamKhesaratSales?: string,
        public adamKhesaratId?: number,
        public adamKhesaratSarneshinSarneshin?: string,
        public adamKhesaratSarneshinId?: number,
        public khesaratSrneshinNerkhKhesarat?: string,
        public khesaratSrneshinId?: number,
        public khesaratSalesNerkhKhesarat?: string,
        public khesaratSalesId?: number
    ) {}
}
