import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    TipKhodroBimishoComponent,
    TipKhodroBimishoDetailComponent,
    TipKhodroBimishoUpdateComponent,
    TipKhodroBimishoDeletePopupComponent,
    TipKhodroBimishoDeleteDialogComponent,
    tipKhodroRoute,
    tipKhodroPopupRoute
} from './';

const ENTITY_STATES = [...tipKhodroRoute, ...tipKhodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipKhodroBimishoComponent,
        TipKhodroBimishoDetailComponent,
        TipKhodroBimishoUpdateComponent,
        TipKhodroBimishoDeleteDialogComponent,
        TipKhodroBimishoDeletePopupComponent
    ],
    entryComponents: [
        TipKhodroBimishoComponent,
        TipKhodroBimishoUpdateComponent,
        TipKhodroBimishoDeleteDialogComponent,
        TipKhodroBimishoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartTipKhodroBimishoModule {}
