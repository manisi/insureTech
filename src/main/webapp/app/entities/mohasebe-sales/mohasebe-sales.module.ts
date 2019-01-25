import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    MohasebeSalesComponent,
    MohasebeSalesDetailComponent,
    MohasebeSalesUpdateComponent,
    MohasebeSalesDeletePopupComponent,
    MohasebeSalesDeleteDialogComponent,
    mohasebeSalesRoute,
    mohasebeSalesPopupRoute
} from './';

const ENTITY_STATES = [...mohasebeSalesRoute, ...mohasebeSalesPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MohasebeSalesComponent,
        MohasebeSalesDetailComponent,
        MohasebeSalesUpdateComponent,
        MohasebeSalesDeleteDialogComponent,
        MohasebeSalesDeletePopupComponent
    ],
    entryComponents: [
        MohasebeSalesComponent,
        MohasebeSalesUpdateComponent,
        MohasebeSalesDeleteDialogComponent,
        MohasebeSalesDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartMohasebeSalesModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
