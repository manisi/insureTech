import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAshkhasBimisho } from 'app/shared/model/ashkhas-bimisho.model';

@Component({
    selector: 'insutech-ashkhas-bimisho-detail',
    templateUrl: './ashkhas-bimisho-detail.component.html'
})
export class AshkhasBimishoDetailComponent implements OnInit {
    ashkhas: IAshkhasBimisho;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ashkhas }) => {
            this.ashkhas = ashkhas;
        });
    }

    previousState() {
        window.history.back();
    }
}
