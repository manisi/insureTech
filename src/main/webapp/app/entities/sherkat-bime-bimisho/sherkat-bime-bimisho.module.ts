import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SherkatBimeBimishoComponent,
    SherkatBimeBimishoDetailComponent,
    SherkatBimeBimishoUpdateComponent,
    SherkatBimeBimishoDeletePopupComponent,
    SherkatBimeBimishoDeleteDialogComponent,
    sherkatBimeRoute,
    sherkatBimePopupRoute
} from './';

const ENTITY_STATES = [...sherkatBimeRoute, ...sherkatBimePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SherkatBimeBimishoComponent,
        SherkatBimeBimishoDetailComponent,
        SherkatBimeBimishoUpdateComponent,
        SherkatBimeBimishoDeleteDialogComponent,
        SherkatBimeBimishoDeletePopupComponent
    ],
    entryComponents: [
        SherkatBimeBimishoComponent,
        SherkatBimeBimishoUpdateComponent,
        SherkatBimeBimishoDeleteDialogComponent,
        SherkatBimeBimishoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSherkatBimeBimishoModule {}
