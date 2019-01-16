import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    CountryBimishoComponent,
    CountryBimishoDetailComponent,
    CountryBimishoUpdateComponent,
    CountryBimishoDeletePopupComponent,
    CountryBimishoDeleteDialogComponent,
    countryRoute,
    countryPopupRoute
} from './';

const ENTITY_STATES = [...countryRoute, ...countryPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CountryBimishoComponent,
        CountryBimishoDetailComponent,
        CountryBimishoUpdateComponent,
        CountryBimishoDeleteDialogComponent,
        CountryBimishoDeletePopupComponent
    ],
    entryComponents: [
        CountryBimishoComponent,
        CountryBimishoUpdateComponent,
        CountryBimishoDeleteDialogComponent,
        CountryBimishoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartCountryBimishoModule {}
