import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';

@Component({
    selector: 'jhi-adam-khesarat-badane-detail',
    templateUrl: './adam-khesarat-badane-detail.component.html'
})
export class AdamKhesaratBadaneDetailComponent implements OnInit {
    adamKhesaratBadane: IAdamKhesaratBadane;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adamKhesaratBadane }) => {
            this.adamKhesaratBadane = adamKhesaratBadane;
        });
    }

    previousState() {
        window.history.back();
    }
}
