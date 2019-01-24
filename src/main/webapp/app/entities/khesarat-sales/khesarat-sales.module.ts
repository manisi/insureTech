import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from 'app/shared';
import {
    KhesaratSalesComponent,
    KhesaratSalesDetailComponent,
    KhesaratSalesUpdateComponent,
    KhesaratSalesDeletePopupComponent,
    KhesaratSalesDeleteDialogComponent,
    khesaratSalesRoute,
    khesaratSalesPopupRoute
} from './';

const ENTITY_STATES = [...khesaratSalesRoute, ...khesaratSalesPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KhesaratSalesComponent,
        KhesaratSalesDetailComponent,
        KhesaratSalesUpdateComponent,
        KhesaratSalesDeleteDialogComponent,
        KhesaratSalesDeletePopupComponent
    ],
    entryComponents: [
        KhesaratSalesComponent,
        KhesaratSalesUpdateComponent,
        KhesaratSalesDeleteDialogComponent,
        KhesaratSalesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKhesaratSalesModule {}
