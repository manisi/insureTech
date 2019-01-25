import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipKhodro } from 'app/shared/model/tip-khodro.model';

@Component({
    selector: 'jhi-tip-khodro-detail',
    templateUrl: './tip-khodro-detail.component.html'
})
export class TipKhodroDetailComponent implements OnInit {
    tipKhodro: ITipKhodro;

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
