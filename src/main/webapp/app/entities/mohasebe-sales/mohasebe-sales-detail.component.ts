import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMohasebeSales } from 'app/shared/model/mohasebe-sales.model';

@Component({
    selector: 'jhi-mohasebe-sales-detail',
    templateUrl: './mohasebe-sales-detail.component.html'
})
export class MohasebeSalesDetailComponent implements OnInit {
    mohasebeSales: IMohasebeSales;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mohasebeSales }) => {
            this.mohasebeSales = mohasebeSales;
        });
    }

    previousState() {
        window.history.back();
    }
}
