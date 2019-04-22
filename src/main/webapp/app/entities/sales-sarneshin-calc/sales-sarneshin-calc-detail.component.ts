import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';

@Component({
    selector: 'jhi-sales-sarneshin-calc-detail',
    templateUrl: './sales-sarneshin-calc-detail.component.html'
})
export class SalesSarneshinCalcDetailComponent implements OnInit {
    salesSarneshinCalc: ISalesSarneshinCalc;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ salesSarneshinCalc }) => {
            this.salesSarneshinCalc = salesSarneshinCalc;
        });
    }

    previousState() {
        window.history.back();
    }
}
