import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPooshesh } from 'app/shared/model/pooshesh.model';

@Component({
    selector: 'jhi-pooshesh-detail',
    templateUrl: './pooshesh-detail.component.html'
})
export class PoosheshDetailComponent implements OnInit {
    pooshesh: IPooshesh;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pooshesh }) => {
            this.pooshesh = pooshesh;
        });
    }

    previousState() {
        window.history.back();
    }
}
