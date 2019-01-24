import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKhesaratSales } from 'app/shared/model/khesarat-sales.model';

@Component({
    selector: 'jhi-khesarat-sales-detail',
    templateUrl: './khesarat-sales-detail.component.html'
})
export class KhesaratSalesDetailComponent implements OnInit {
    khesaratSales: IKhesaratSales;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khesaratSales }) => {
            this.khesaratSales = khesaratSales;
        });
    }

    previousState() {
        window.history.back();
    }
}
