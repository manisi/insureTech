import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    TipKhodroComponent,
    TipKhodroDetailComponent,
    TipKhodroUpdateComponent,
    TipKhodroDeletePopupComponent,
    TipKhodroDeleteDialogComponent,
    tipKhodroRoute,
    tipKhodroPopupRoute
} from './';

const ENTITY_STATES = [...tipKhodroRoute, ...tipKhodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipKhodroComponent,
        TipKhodroDetailComponent,
        TipKhodroUpdateComponent,
        TipKhodroDeleteDialogComponent,
        TipKhodroDeletePopupComponent
    ],
    entryComponents: [TipKhodroComponent, TipKhodroUpdateComponent, TipKhodroDeleteDialogComponent, TipKhodroDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartTipKhodroModule {}
