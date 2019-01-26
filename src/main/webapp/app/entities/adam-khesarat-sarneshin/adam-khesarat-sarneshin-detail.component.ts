import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';

@Component({
    selector: 'jhi-adam-khesarat-sarneshin-detail',
    templateUrl: './adam-khesarat-sarneshin-detail.component.html'
})
export class AdamKhesaratSarneshinDetailComponent implements OnInit {
    adamKhesaratSarneshin: IAdamKhesaratSarneshin;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adamKhesaratSarneshin }) => {
            this.adamKhesaratSarneshin = adamKhesaratSarneshin;
        });
    }

    previousState() {
        window.history.back();
    }
}
