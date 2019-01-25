import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    JarimeDirkardComponent,
    JarimeDirkardDetailComponent,
    JarimeDirkardUpdateComponent,
    JarimeDirkardDeletePopupComponent,
    JarimeDirkardDeleteDialogComponent,
    jarimeDirkardRoute,
    jarimeDirkardPopupRoute
} from './';

const ENTITY_STATES = [...jarimeDirkardRoute, ...jarimeDirkardPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JarimeDirkardComponent,
        JarimeDirkardDetailComponent,
        JarimeDirkardUpdateComponent,
        JarimeDirkardDeleteDialogComponent,
        JarimeDirkardDeletePopupComponent
    ],
    entryComponents: [
        JarimeDirkardComponent,
        JarimeDirkardUpdateComponent,
        JarimeDirkardDeleteDialogComponent,
        JarimeDirkardDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartJarimeDirkardModule {}
