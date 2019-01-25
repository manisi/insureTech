import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISabeghe } from 'app/shared/model/sabeghe.model';

@Component({
    selector: 'jhi-sabeghe-detail',
    templateUrl: './sabeghe-detail.component.html'
})
export class SabegheDetailComponent implements OnInit {
    sabeghe: ISabeghe;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sabeghe }) => {
            this.sabeghe = sabeghe;
        });
    }

    previousState() {
        window.history.back();
    }
}
