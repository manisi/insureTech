import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMoredEstefadeSales } from 'app/shared/model/mored-estefade-sales.model';

@Component({
    selector: 'jhi-mored-estefade-sales-detail',
    templateUrl: './mored-estefade-sales-detail.component.html'
})
export class MoredEstefadeSalesDetailComponent implements OnInit {
    moredEstefadeSales: IMoredEstefadeSales;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ moredEstefadeSales }) => {
            this.moredEstefadeSales = moredEstefadeSales;
        });
    }

    previousState() {
        window.history.back();
    }
}
