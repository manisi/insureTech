import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipKhodroBimisho } from 'app/shared/model/tip-khodro-bimisho.model';

@Component({
    selector: 'insutech-tip-khodro-bimisho-detail',
    templateUrl: './tip-khodro-bimisho-detail.component.html'
})
export class TipKhodroBimishoDetailComponent implements OnInit {
    tipKhodro: ITipKhodroBimisho;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipKhodro }) => {
            this.tipKhodro = tipKhodro;
        });
    }

    previousState() {
        window.history.back();
    }
}
