import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICountryBimisho } from 'app/shared/model/country-bimisho.model';
import { CountryBimishoService } from './country-bimisho.service';

@Component({
    selector: 'insutech-country-bimisho-update',
    templateUrl: './country-bimisho-update.component.html'
})
export class CountryBimishoUpdateComponent implements OnInit {
    country: ICountryBimisho;
    isSaving: boolean;

    constructor(protected countryService: CountryBimishoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ country }) => {
            this.country = country;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.country.id !== undefined) {
            this.subscribeToSaveResponse(this.countryService.update(this.country));
        } else {
            this.subscribeToSaveResponse(this.countryService.create(this.country));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICountryBimisho>>) {
        result.subscribe((res: HttpResponse<ICountryBimisho>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
