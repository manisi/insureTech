import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';

@Component({
    selector: 'jhi-noe-sabeghe-detail',
    templateUrl: './noe-sabeghe-detail.component.html'
})
export class NoeSabegheDetailComponent implements OnInit {
    noeSabeghe: INoeSabeghe;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ noeSabeghe }) => {
            this.noeSabeghe = noeSabeghe;
        });
    }

    previousState() {
        window.history.back();
    }
}
