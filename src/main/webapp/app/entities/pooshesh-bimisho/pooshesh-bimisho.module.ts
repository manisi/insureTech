import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    PoosheshBimishoComponent,
    PoosheshBimishoDetailComponent,
    PoosheshBimishoUpdateComponent,
    PoosheshBimishoDeletePopupComponent,
    PoosheshBimishoDeleteDialogComponent,
    poosheshRoute,
    poosheshPopupRoute
} from './';

const ENTITY_STATES = [...poosheshRoute, ...poosheshPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PoosheshBimishoComponent,
        PoosheshBimishoDetailComponent,
        PoosheshBimishoUpdateComponent,
        PoosheshBimishoDeleteDialogComponent,
        PoosheshBimishoDeletePopupComponent
    ],
    entryComponents: [
        PoosheshBimishoComponent,
        PoosheshBimishoUpdateComponent,
        PoosheshBimishoDeleteDialogComponent,
        PoosheshBimishoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartPoosheshBimishoModule {}
