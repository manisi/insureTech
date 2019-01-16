import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICityBimisho } from 'app/shared/model/city-bimisho.model';

@Component({
    selector: 'insutech-city-bimisho-detail',
    templateUrl: './city-bimisho-detail.component.html'
})
export class CityBimishoDetailComponent implements OnInit {
    city: ICityBimisho;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ city }) => {
            this.city = city;
        });
    }

    previousState() {
        window.history.back();
    }
}
