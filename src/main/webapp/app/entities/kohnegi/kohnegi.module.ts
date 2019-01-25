import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    KohnegiComponent,
    KohnegiDetailComponent,
    KohnegiUpdateComponent,
    KohnegiDeletePopupComponent,
    KohnegiDeleteDialogComponent,
    kohnegiRoute,
    kohnegiPopupRoute
} from './';

const ENTITY_STATES = [...kohnegiRoute, ...kohnegiPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KohnegiComponent,
        KohnegiDetailComponent,
        KohnegiUpdateComponent,
        KohnegiDeleteDialogComponent,
        KohnegiDeletePopupComponent
    ],
    entryComponents: [KohnegiComponent, KohnegiUpdateComponent, KohnegiDeleteDialogComponent, KohnegiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKohnegiModule {}
