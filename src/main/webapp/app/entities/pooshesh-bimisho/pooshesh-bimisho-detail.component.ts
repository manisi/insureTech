import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPoosheshBimisho } from 'app/shared/model/pooshesh-bimisho.model';

@Component({
    selector: 'insutech-pooshesh-bimisho-detail',
    templateUrl: './pooshesh-bimisho-detail.component.html'
})
export class PoosheshBimishoDetailComponent implements OnInit {
    pooshesh: IPoosheshBimisho;

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
