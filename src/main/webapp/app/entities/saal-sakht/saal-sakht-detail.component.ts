import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISaalSakht } from 'app/shared/model/saal-sakht.model';

@Component({
    selector: 'jhi-saal-sakht-detail',
    templateUrl: './saal-sakht-detail.component.html'
})
export class SaalSakhtDetailComponent implements OnInit {
    saalSakht: ISaalSakht;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ saalSakht }) => {
            this.saalSakht = saalSakht;
        });
    }

    previousState() {
        window.history.back();
    }
}
