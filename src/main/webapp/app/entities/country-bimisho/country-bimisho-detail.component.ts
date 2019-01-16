import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICountryBimisho } from 'app/shared/model/country-bimisho.model';

@Component({
    selector: 'insutech-country-bimisho-detail',
    templateUrl: './country-bimisho-detail.component.html'
})
export class CountryBimishoDetailComponent implements OnInit {
    country: ICountryBimisho;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ country }) => {
            this.country = country;
        });
    }

    previousState() {
        window.history.back();
    }
}
