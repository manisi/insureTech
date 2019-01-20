import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    MohasebeSalesComponent,
    MohasebeSalesDetailComponent,
    MohasebeSalesUpdateComponent,
    MohasebeSalesDeletePopupComponent,
    MohasebeSalesDeleteDialogComponent,
    mohasebeSalesRoute,
    mohasebeSalesPopupRoute
} from './';

const ENTITY_STATES = [...mohasebeSalesRoute, ...mohasebeSalesPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MohasebeSalesComponent,
        MohasebeSalesDetailComponent,
        MohasebeSalesUpdateComponent,
        MohasebeSalesDeleteDialogComponent,
        MohasebeSalesDeletePopupComponent
    ],
    entryComponents: [
        MohasebeSalesComponent,
        MohasebeSalesUpdateComponent,
        MohasebeSalesDeleteDialogComponent,
        MohasebeSalesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartMohasebeSalesModule {}
