import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';

@Component({
    selector: 'jhi-grouh-khodro-detail',
    templateUrl: './grouh-khodro-detail.component.html'
})
export class GrouhKhodroDetailComponent implements OnInit {
    grouhKhodro: IGrouhKhodro;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ grouhKhodro }) => {
            this.grouhKhodro = grouhKhodro;
        });
    }

    previousState() {
        window.history.back();
    }
}
