import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    EstelaamSalesNerkhComponent,
    EstelaamSalesNerkhDetailComponent,
    EstelaamSalesNerkhUpdateComponent,
    EstelaamSalesNerkhDeletePopupComponent,
    EstelaamSalesNerkhDeleteDialogComponent,
    estelaamSalesNerkhRoute,
    estelaamSalesNerkhPopupRoute
} from './';

const ENTITY_STATES = [...estelaamSalesNerkhRoute, ...estelaamSalesNerkhPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EstelaamSalesNerkhComponent,
        EstelaamSalesNerkhDetailComponent,
        EstelaamSalesNerkhUpdateComponent,
        EstelaamSalesNerkhDeleteDialogComponent,
        EstelaamSalesNerkhDeletePopupComponent
    ],
    entryComponents: [
        EstelaamSalesNerkhComponent,
        EstelaamSalesNerkhUpdateComponent,
        EstelaamSalesNerkhDeleteDialogComponent,
        EstelaamSalesNerkhDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartEstelaamSalesNerkhModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
