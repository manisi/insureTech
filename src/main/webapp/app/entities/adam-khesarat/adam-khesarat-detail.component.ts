import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdamKhesarat } from 'app/shared/model/adam-khesarat.model';

@Component({
    selector: 'jhi-adam-khesarat-detail',
    templateUrl: './adam-khesarat-detail.component.html'
})
export class AdamKhesaratDetailComponent implements OnInit {
    adamKhesarat: IAdamKhesarat;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adamKhesarat }) => {
            this.adamKhesarat = adamKhesarat;
        });
    }

    previousState() {
        window.history.back();
    }
}
