import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    AshkhasBimishoComponent,
    AshkhasBimishoDetailComponent,
    AshkhasBimishoUpdateComponent,
    AshkhasBimishoDeletePopupComponent,
    AshkhasBimishoDeleteDialogComponent,
    ashkhasRoute,
    ashkhasPopupRoute
} from './';

const ENTITY_STATES = [...ashkhasRoute, ...ashkhasPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AshkhasBimishoComponent,
        AshkhasBimishoDetailComponent,
        AshkhasBimishoUpdateComponent,
        AshkhasBimishoDeleteDialogComponent,
        AshkhasBimishoDeletePopupComponent
    ],
    entryComponents: [
        AshkhasBimishoComponent,
        AshkhasBimishoUpdateComponent,
        AshkhasBimishoDeleteDialogComponent,
        AshkhasBimishoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartAshkhasBimishoModule {}
