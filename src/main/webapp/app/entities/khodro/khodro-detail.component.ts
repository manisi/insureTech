import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKhodro } from 'app/shared/model/khodro.model';

@Component({
    selector: 'jhi-khodro-detail',
    templateUrl: './khodro-detail.component.html'
})
export class KhodroDetailComponent implements OnInit {
    khodro: IKhodro;

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
