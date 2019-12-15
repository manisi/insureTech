import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';

@Component({
    selector: 'jhi-sabeghe-khesarat-detail',
    templateUrl: './sabeghe-khesarat-detail.component.html'
})
export class SabegheKhesaratDetailComponent implements OnInit {
    sabegheKhesarat: ISabegheKhesarat;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sabegheKhesarat }) => {
            this.sabegheKhesarat = sabegheKhesarat;
        });
    }

    previousState() {
        window.history.back();
    }
}
