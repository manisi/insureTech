import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SabegheComponent,
    SabegheDetailComponent,
    SabegheUpdateComponent,
    SabegheDeletePopupComponent,
    SabegheDeleteDialogComponent,
    sabegheRoute,
    sabeghePopupRoute
} from './';

const ENTITY_STATES = [...sabegheRoute, ...sabeghePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SabegheComponent,
        SabegheDetailComponent,
        SabegheUpdateComponent,
        SabegheDeleteDialogComponent,
        SabegheDeletePopupComponent
    ],
    entryComponents: [SabegheComponent, SabegheUpdateComponent, SabegheDeleteDialogComponent, SabegheDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSabegheModule {}
