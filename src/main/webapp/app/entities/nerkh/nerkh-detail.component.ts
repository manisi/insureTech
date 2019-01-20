import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INerkh } from 'app/shared/model/nerkh.model';

@Component({
    selector: 'insutech-nerkh-detail',
    templateUrl: './nerkh-detail.component.html'
})
export class NerkhDetailComponent implements OnInit {
    nerkh: INerkh;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nerkh }) => {
            this.nerkh = nerkh;
        });
    }

    previousState() {
        window.history.back();
    }
}
