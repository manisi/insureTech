import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    OnvanKhodroComponent,
    OnvanKhodroDetailComponent,
    OnvanKhodroUpdateComponent,
    OnvanKhodroDeletePopupComponent,
    OnvanKhodroDeleteDialogComponent,
    onvanKhodroRoute,
    onvanKhodroPopupRoute
} from './';

const ENTITY_STATES = [...onvanKhodroRoute, ...onvanKhodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OnvanKhodroComponent,
        OnvanKhodroDetailComponent,
        OnvanKhodroUpdateComponent,
        OnvanKhodroDeleteDialogComponent,
        OnvanKhodroDeletePopupComponent
    ],
    entryComponents: [OnvanKhodroComponent, OnvanKhodroUpdateComponent, OnvanKhodroDeleteDialogComponent, OnvanKhodroDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartOnvanKhodroModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
