import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    GrouhKhodroComponent,
    GrouhKhodroDetailComponent,
    GrouhKhodroUpdateComponent,
    GrouhKhodroDeletePopupComponent,
    GrouhKhodroDeleteDialogComponent,
    grouhKhodroRoute,
    grouhKhodroPopupRoute
} from './';

const ENTITY_STATES = [...grouhKhodroRoute, ...grouhKhodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GrouhKhodroComponent,
        GrouhKhodroDetailComponent,
        GrouhKhodroUpdateComponent,
        GrouhKhodroDeleteDialogComponent,
        GrouhKhodroDeletePopupComponent
    ],
    entryComponents: [GrouhKhodroComponent, GrouhKhodroUpdateComponent, GrouhKhodroDeleteDialogComponent, GrouhKhodroDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartGrouhKhodroModule {}
