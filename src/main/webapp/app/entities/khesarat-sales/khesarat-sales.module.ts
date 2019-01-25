import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

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
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKhesaratSalesModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
