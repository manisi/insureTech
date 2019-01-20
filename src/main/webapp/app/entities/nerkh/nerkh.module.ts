import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    NerkhComponent,
    NerkhDetailComponent,
    NerkhUpdateComponent,
    NerkhDeletePopupComponent,
    NerkhDeleteDialogComponent,
    nerkhRoute,
    nerkhPopupRoute
} from './';

const ENTITY_STATES = [...nerkhRoute, ...nerkhPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [NerkhComponent, NerkhDetailComponent, NerkhUpdateComponent, NerkhDeleteDialogComponent, NerkhDeletePopupComponent],
    entryComponents: [NerkhComponent, NerkhUpdateComponent, NerkhDeleteDialogComponent, NerkhDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartNerkhModule {}
