import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    MohasebeBadaneComponent,
    MohasebeBadaneDetailComponent,
    MohasebeBadaneUpdateComponent,
    MohasebeBadaneDeletePopupComponent,
    MohasebeBadaneDeleteDialogComponent,
    mohasebeBadaneRoute,
    mohasebeBadanePopupRoute
} from './';

const ENTITY_STATES = [...mohasebeBadaneRoute, ...mohasebeBadanePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MohasebeBadaneComponent,
        MohasebeBadaneDetailComponent,
        MohasebeBadaneUpdateComponent,
        MohasebeBadaneDeleteDialogComponent,
        MohasebeBadaneDeletePopupComponent
    ],
    entryComponents: [
        MohasebeBadaneComponent,
        MohasebeBadaneUpdateComponent,
        MohasebeBadaneDeleteDialogComponent,
        MohasebeBadaneDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartMohasebeBadaneModule {}
