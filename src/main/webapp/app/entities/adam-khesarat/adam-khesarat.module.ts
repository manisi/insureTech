import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    AdamKhesaratComponent,
    AdamKhesaratDetailComponent,
    AdamKhesaratUpdateComponent,
    AdamKhesaratDeletePopupComponent,
    AdamKhesaratDeleteDialogComponent,
    adamKhesaratRoute,
    adamKhesaratPopupRoute
} from './';

const ENTITY_STATES = [...adamKhesaratRoute, ...adamKhesaratPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdamKhesaratComponent,
        AdamKhesaratDetailComponent,
        AdamKhesaratUpdateComponent,
        AdamKhesaratDeleteDialogComponent,
        AdamKhesaratDeletePopupComponent
    ],
    entryComponents: [
        AdamKhesaratComponent,
        AdamKhesaratUpdateComponent,
        AdamKhesaratDeleteDialogComponent,
        AdamKhesaratDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartAdamKhesaratModule {}
