import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJarimeDirkard } from 'app/shared/model/jarime-dirkard.model';

@Component({
    selector: 'jhi-jarime-dirkard-detail',
    templateUrl: './jarime-dirkard-detail.component.html'
})
export class JarimeDirkardDetailComponent implements OnInit {
    jarimeDirkard: IJarimeDirkard;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jarimeDirkard }) => {
            this.jarimeDirkard = jarimeDirkard;
        });
    }

    previousState() {
        window.history.back();
    }
}
