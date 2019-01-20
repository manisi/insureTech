import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    KhodroComponent,
    KhodroDetailComponent,
    KhodroUpdateComponent,
    KhodroDeletePopupComponent,
    KhodroDeleteDialogComponent,
    khodroRoute,
    khodroPopupRoute
} from './';

const ENTITY_STATES = [...khodroRoute, ...khodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [KhodroComponent, KhodroDetailComponent, KhodroUpdateComponent, KhodroDeleteDialogComponent, KhodroDeletePopupComponent],
    entryComponents: [KhodroComponent, KhodroUpdateComponent, KhodroDeleteDialogComponent, KhodroDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKhodroModule {}
