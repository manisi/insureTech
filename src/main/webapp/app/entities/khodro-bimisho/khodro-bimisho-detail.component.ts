import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKhodroBimisho } from 'app/shared/model/khodro-bimisho.model';

@Component({
    selector: 'insutech-khodro-bimisho-detail',
    templateUrl: './khodro-bimisho-detail.component.html'
})
export class KhodroBimishoDetailComponent implements OnInit {
    khodro: IKhodroBimisho;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khodro }) => {
            this.khodro = khodro;
        });
    }

    previousState() {
        window.history.back();
    }
}
