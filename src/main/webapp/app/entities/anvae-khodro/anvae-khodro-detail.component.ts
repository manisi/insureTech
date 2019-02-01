import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnvaeKhodro } from 'app/shared/model/anvae-khodro.model';

@Component({
    selector: 'jhi-anvae-khodro-detail',
    templateUrl: './anvae-khodro-detail.component.html'
})
export class AnvaeKhodroDetailComponent implements OnInit {
    anvaeKhodro: IAnvaeKhodro;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ anvaeKhodro }) => {
            this.anvaeKhodro = anvaeKhodro;
        });
    }

    previousState() {
        window.history.back();
    }
}
