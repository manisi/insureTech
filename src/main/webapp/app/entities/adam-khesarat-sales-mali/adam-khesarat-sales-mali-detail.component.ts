import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';

@Component({
    selector: 'jhi-adam-khesarat-sales-mali-detail',
    templateUrl: './adam-khesarat-sales-mali-detail.component.html'
})
export class AdamKhesaratSalesMaliDetailComponent implements OnInit {
    adamKhesaratSalesMali: IAdamKhesaratSalesMali;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adamKhesaratSalesMali }) => {
            this.adamKhesaratSalesMali = adamKhesaratSalesMali;
        });
    }

    previousState() {
        window.history.back();
    }
}
