import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    PoosheshComponent,
    PoosheshDetailComponent,
    PoosheshUpdateComponent,
    PoosheshDeletePopupComponent,
    PoosheshDeleteDialogComponent,
    poosheshRoute,
    poosheshPopupRoute
} from './';

const ENTITY_STATES = [...poosheshRoute, ...poosheshPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PoosheshComponent,
        PoosheshDetailComponent,
        PoosheshUpdateComponent,
        PoosheshDeleteDialogComponent,
        PoosheshDeletePopupComponent
    ],
    entryComponents: [PoosheshComponent, PoosheshUpdateComponent, PoosheshDeleteDialogComponent, PoosheshDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartPoosheshModule {}
