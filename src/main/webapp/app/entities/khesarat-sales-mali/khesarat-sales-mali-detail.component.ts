import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';

@Component({
    selector: 'jhi-khesarat-sales-mali-detail',
    templateUrl: './khesarat-sales-mali-detail.component.html'
})
export class KhesaratSalesMaliDetailComponent implements OnInit {
    khesaratSalesMali: IKhesaratSalesMali;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khesaratSalesMali }) => {
            this.khesaratSalesMali = khesaratSalesMali;
        });
    }

    previousState() {
        window.history.back();
    }
}
