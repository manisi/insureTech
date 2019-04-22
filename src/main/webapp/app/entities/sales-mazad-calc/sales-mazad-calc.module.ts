import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SalesMazadCalcComponent,
    SalesMazadCalcDetailComponent,
    SalesMazadCalcUpdateComponent,
    SalesMazadCalcDeletePopupComponent,
    SalesMazadCalcDeleteDialogComponent,
    salesMazadCalcRoute,
    salesMazadCalcPopupRoute
} from './';

const ENTITY_STATES = [...salesMazadCalcRoute, ...salesMazadCalcPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SalesMazadCalcComponent,
        SalesMazadCalcDetailComponent,
        SalesMazadCalcUpdateComponent,
        SalesMazadCalcDeleteDialogComponent,
        SalesMazadCalcDeletePopupComponent
    ],
    entryComponents: [
        SalesMazadCalcComponent,
        SalesMazadCalcUpdateComponent,
        SalesMazadCalcDeleteDialogComponent,
        SalesMazadCalcDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSalesMazadCalcModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
