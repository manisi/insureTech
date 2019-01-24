import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    NoeSabegheComponent,
    NoeSabegheDetailComponent,
    NoeSabegheUpdateComponent,
    NoeSabegheDeletePopupComponent,
    NoeSabegheDeleteDialogComponent,
    noeSabegheRoute,
    noeSabeghePopupRoute
} from './';

const ENTITY_STATES = [...noeSabegheRoute, ...noeSabeghePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NoeSabegheComponent,
        NoeSabegheDetailComponent,
        NoeSabegheUpdateComponent,
        NoeSabegheDeleteDialogComponent,
        NoeSabegheDeletePopupComponent
    ],
    entryComponents: [NoeSabegheComponent, NoeSabegheUpdateComponent, NoeSabegheDeleteDialogComponent, NoeSabegheDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartNoeSabegheModule {}
