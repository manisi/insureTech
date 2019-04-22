import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISalesJaniCalc } from 'app/shared/model/sales-jani-calc.model';

@Component({
    selector: 'jhi-sales-jani-calc-detail',
    templateUrl: './sales-jani-calc-detail.component.html'
})
export class SalesJaniCalcDetailComponent implements OnInit {
    salesJaniCalc: ISalesJaniCalc;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ salesJaniCalc }) => {
            this.salesJaniCalc = salesJaniCalc;
        });
    }

    previousState() {
        window.history.back();
    }
}
