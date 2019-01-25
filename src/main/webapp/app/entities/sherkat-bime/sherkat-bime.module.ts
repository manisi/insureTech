import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SherkatBimeComponent,
    SherkatBimeDetailComponent,
    SherkatBimeUpdateComponent,
    SherkatBimeDeletePopupComponent,
    SherkatBimeDeleteDialogComponent,
    sherkatBimeRoute,
    sherkatBimePopupRoute
} from './';

const ENTITY_STATES = [...sherkatBimeRoute, ...sherkatBimePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SherkatBimeComponent,
        SherkatBimeDetailComponent,
        SherkatBimeUpdateComponent,
        SherkatBimeDeleteDialogComponent,
        SherkatBimeDeletePopupComponent
    ],
    entryComponents: [SherkatBimeComponent, SherkatBimeUpdateComponent, SherkatBimeDeleteDialogComponent, SherkatBimeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSherkatBimeModule {}
