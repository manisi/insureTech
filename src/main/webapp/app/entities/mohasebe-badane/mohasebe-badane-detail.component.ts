import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMohasebeBadane } from 'app/shared/model/mohasebe-badane.model';

@Component({
    selector: 'insutech-mohasebe-badane-detail',
    templateUrl: './mohasebe-badane-detail.component.html'
})
export class MohasebeBadaneDetailComponent implements OnInit {
    mohasebeBadane: IMohasebeBadane;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mohasebeBadane }) => {
            this.mohasebeBadane = mohasebeBadane;
        });
    }

    previousState() {
        window.history.back();
    }
}
