import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    CityBimishoComponent,
    CityBimishoDetailComponent,
    CityBimishoUpdateComponent,
    CityBimishoDeletePopupComponent,
    CityBimishoDeleteDialogComponent,
    cityRoute,
    cityPopupRoute
} from './';

const ENTITY_STATES = [...cityRoute, ...cityPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CityBimishoComponent,
        CityBimishoDetailComponent,
        CityBimishoUpdateComponent,
        CityBimishoDeleteDialogComponent,
        CityBimishoDeletePopupComponent
    ],
    entryComponents: [CityBimishoComponent, CityBimishoUpdateComponent, CityBimishoDeleteDialogComponent, CityBimishoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartCityBimishoModule {}
