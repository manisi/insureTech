import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    AshkhasComponent,
    AshkhasDetailComponent,
    AshkhasUpdateComponent,
    AshkhasDeletePopupComponent,
    AshkhasDeleteDialogComponent,
    ashkhasRoute,
    ashkhasPopupRoute
} from './';

const ENTITY_STATES = [...ashkhasRoute, ...ashkhasPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AshkhasComponent,
        AshkhasDetailComponent,
        AshkhasUpdateComponent,
        AshkhasDeleteDialogComponent,
        AshkhasDeletePopupComponent
    ],
    entryComponents: [AshkhasComponent, AshkhasUpdateComponent, AshkhasDeleteDialogComponent, AshkhasDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartAshkhasModule {}
