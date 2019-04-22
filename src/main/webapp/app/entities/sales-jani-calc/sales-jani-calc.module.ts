import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SalesJaniCalcComponent,
    SalesJaniCalcDetailComponent,
    SalesJaniCalcUpdateComponent,
    SalesJaniCalcDeletePopupComponent,
    SalesJaniCalcDeleteDialogComponent,
    salesJaniCalcRoute,
    salesJaniCalcPopupRoute
} from './';

const ENTITY_STATES = [...salesJaniCalcRoute, ...salesJaniCalcPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SalesJaniCalcComponent,
        SalesJaniCalcDetailComponent,
        SalesJaniCalcUpdateComponent,
        SalesJaniCalcDeleteDialogComponent,
        SalesJaniCalcDeletePopupComponent
    ],
    entryComponents: [
        SalesJaniCalcComponent,
        SalesJaniCalcUpdateComponent,
        SalesJaniCalcDeleteDialogComponent,
        SalesJaniCalcDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSalesJaniCalcModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
