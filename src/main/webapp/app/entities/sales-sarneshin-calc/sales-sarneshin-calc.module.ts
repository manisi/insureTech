import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SalesSarneshinCalcComponent,
    SalesSarneshinCalcDetailComponent,
    SalesSarneshinCalcUpdateComponent,
    SalesSarneshinCalcDeletePopupComponent,
    SalesSarneshinCalcDeleteDialogComponent,
    salesSarneshinCalcRoute,
    salesSarneshinCalcPopupRoute
} from './';

const ENTITY_STATES = [...salesSarneshinCalcRoute, ...salesSarneshinCalcPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SalesSarneshinCalcComponent,
        SalesSarneshinCalcDetailComponent,
        SalesSarneshinCalcUpdateComponent,
        SalesSarneshinCalcDeleteDialogComponent,
        SalesSarneshinCalcDeletePopupComponent
    ],
    entryComponents: [
        SalesSarneshinCalcComponent,
        SalesSarneshinCalcUpdateComponent,
        SalesSarneshinCalcDeleteDialogComponent,
        SalesSarneshinCalcDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSalesSarneshinCalcModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
