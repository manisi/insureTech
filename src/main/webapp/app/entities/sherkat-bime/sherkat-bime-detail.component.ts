import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';

@Component({
    selector: 'insutech-sherkat-bime-detail',
    templateUrl: './sherkat-bime-detail.component.html'
})
export class SherkatBimeDetailComponent implements OnInit {
    sherkatBime: ISherkatBime;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sherkatBime }) => {
            this.sherkatBime = sherkatBime;
        });
    }

    previousState() {
        window.history.back();
    }
}
