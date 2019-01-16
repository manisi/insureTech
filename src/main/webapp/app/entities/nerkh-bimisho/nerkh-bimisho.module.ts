import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    NerkhBimishoComponent,
    NerkhBimishoDetailComponent,
    NerkhBimishoUpdateComponent,
    NerkhBimishoDeletePopupComponent,
    NerkhBimishoDeleteDialogComponent,
    nerkhRoute,
    nerkhPopupRoute
} from './';

const ENTITY_STATES = [...nerkhRoute, ...nerkhPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NerkhBimishoComponent,
        NerkhBimishoDetailComponent,
        NerkhBimishoUpdateComponent,
        NerkhBimishoDeleteDialogComponent,
        NerkhBimishoDeletePopupComponent
    ],
    entryComponents: [
        NerkhBimishoComponent,
        NerkhBimishoUpdateComponent,
        NerkhBimishoDeleteDialogComponent,
        NerkhBimishoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartNerkhBimishoModule {}
