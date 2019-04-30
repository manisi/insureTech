import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOnvanKhodro } from 'app/shared/model/onvan-khodro.model';

@Component({
    selector: 'jhi-onvan-khodro-detail',
    templateUrl: './onvan-khodro-detail.component.html'
})
export class OnvanKhodroDetailComponent implements OnInit {
    onvanKhodro: IOnvanKhodro;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ onvanKhodro }) => {
            this.onvanKhodro = onvanKhodro;
        });
    }

    previousState() {
        window.history.back();
    }
}
