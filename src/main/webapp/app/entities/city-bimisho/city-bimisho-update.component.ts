import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICityBimisho } from 'app/shared/model/city-bimisho.model';
import { CityBimishoService } from './city-bimisho.service';
import { ICountryBimisho } from 'app/shared/model/country-bimisho.model';
import { CountryBimishoService } from 'app/entities/country-bimisho';

@Component({
    selector: 'insutech-city-bimisho-update',
    templateUrl: './city-bimisho-update.component.html'
})
export class CityBimishoUpdateComponent implements OnInit {
    city: ICityBimisho;
    isSaving: boolean;

    countries: ICountryBimisho[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cityService: CityBimishoService,
        protected countryService: CountryBimishoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ city }) => {
            this.city = city;
        });
        this.countryService.query().subscribe(
            (res: HttpResponse<ICountryBimisho[]>) => {
                this.countries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.city.id !== undefined) {
            this.subscribeToSaveResponse(this.cityService.update(this.city));
        } else {
            this.subscribeToSaveResponse(this.cityService.create(this.city));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICityBimisho>>) {
        result.subscribe((res: HttpResponse<ICityBimisho>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCountryById(index: number, item: ICountryBimisho) {
        return item.id;
    }
}
