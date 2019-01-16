import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';

@Component({
    selector: 'insutech-nerkh-bimisho-detail',
    templateUrl: './nerkh-bimisho-detail.component.html'
})
export class NerkhBimishoDetailComponent implements OnInit {
    nerkh: INerkhBimisho;

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
