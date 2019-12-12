import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';

@Component({
    selector: 'jhi-estelaam-sales-nerkh-detail',
    templateUrl: './estelaam-sales-nerkh-detail.component.html'
})
export class EstelaamSalesNerkhDetailComponent implements OnInit {
    estelaamSalesNerkh: IEstelaamSalesNerkh;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ estelaamSalesNerkh }) => {
            this.estelaamSalesNerkh = estelaamSalesNerkh;
        });
    }

    previousState() {
        window.history.back();
    }
}
