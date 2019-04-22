import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISalesMazadCalc } from 'app/shared/model/sales-mazad-calc.model';

@Component({
    selector: 'jhi-sales-mazad-calc-detail',
    templateUrl: './sales-mazad-calc-detail.component.html'
})
export class SalesMazadCalcDetailComponent implements OnInit {
    salesMazadCalc: ISalesMazadCalc;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ salesMazadCalc }) => {
            this.salesMazadCalc = salesMazadCalc;
        });
    }

    previousState() {
        window.history.back();
    }
}
