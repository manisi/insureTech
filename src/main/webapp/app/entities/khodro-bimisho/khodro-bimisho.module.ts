import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    KhodroBimishoComponent,
    KhodroBimishoDetailComponent,
    KhodroBimishoUpdateComponent,
    KhodroBimishoDeletePopupComponent,
    KhodroBimishoDeleteDialogComponent,
    khodroRoute,
    khodroPopupRoute
} from './';

const ENTITY_STATES = [...khodroRoute, ...khodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KhodroBimishoComponent,
        KhodroBimishoDetailComponent,
        KhodroBimishoUpdateComponent,
        KhodroBimishoDeleteDialogComponent,
        KhodroBimishoDeletePopupComponent
    ],
    entryComponents: [
        KhodroBimishoComponent,
        KhodroBimishoUpdateComponent,
        KhodroBimishoDeleteDialogComponent,
        KhodroBimishoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKhodroBimishoModule {}
