import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVaziatBime } from 'app/shared/model/vaziat-bime.model';

@Component({
    selector: 'jhi-vaziat-bime-detail',
    templateUrl: './vaziat-bime-detail.component.html'
})
export class VaziatBimeDetailComponent implements OnInit {
    vaziatBime: IVaziatBime;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vaziatBime }) => {
            this.vaziatBime = vaziatBime;
        });
    }

    previousState() {
        window.history.back();
    }
}
