import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    TipKhodroComponent,
    TipKhodroDetailComponent,
    TipKhodroUpdateComponent,
    TipKhodroDeletePopupComponent,
    TipKhodroDeleteDialogComponent,
    tipKhodroRoute,
    tipKhodroPopupRoute
} from './';

const ENTITY_STATES = [...tipKhodroRoute, ...tipKhodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipKhodroComponent,
        TipKhodroDetailComponent,
        TipKhodroUpdateComponent,
        TipKhodroDeleteDialogComponent,
        TipKhodroDeletePopupComponent
    ],
    entryComponents: [TipKhodroComponent, TipKhodroUpdateComponent, TipKhodroDeleteDialogComponent, TipKhodroDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartTipKhodroModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
