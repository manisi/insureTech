import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKhesaratSrneshin } from 'app/shared/model/khesarat-srneshin.model';

@Component({
    selector: 'jhi-khesarat-srneshin-detail',
    templateUrl: './khesarat-srneshin-detail.component.html'
})
export class KhesaratSrneshinDetailComponent implements OnInit {
    khesaratSrneshin: IKhesaratSrneshin;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khesaratSrneshin }) => {
            this.khesaratSrneshin = khesaratSrneshin;
        });
    }

    previousState() {
        window.history.back();
    }
}
