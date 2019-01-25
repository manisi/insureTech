import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAshkhas } from 'app/shared/model/ashkhas.model';

@Component({
    selector: 'jhi-ashkhas-detail',
    templateUrl: './ashkhas-detail.component.html'
})
export class AshkhasDetailComponent implements OnInit {
    ashkhas: IAshkhas;

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
