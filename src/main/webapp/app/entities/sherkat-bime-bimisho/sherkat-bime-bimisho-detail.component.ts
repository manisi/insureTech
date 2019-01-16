import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';

@Component({
    selector: 'insutech-sherkat-bime-bimisho-detail',
    templateUrl: './sherkat-bime-bimisho-detail.component.html'
})
export class SherkatBimeBimishoDetailComponent implements OnInit {
    sherkatBime: ISherkatBimeBimisho;

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
