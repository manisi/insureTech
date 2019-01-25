import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    GrouhKhodroComponent,
    GrouhKhodroDetailComponent,
    GrouhKhodroUpdateComponent,
    GrouhKhodroDeletePopupComponent,
    GrouhKhodroDeleteDialogComponent,
    grouhKhodroRoute,
    grouhKhodroPopupRoute
} from './';

const ENTITY_STATES = [...grouhKhodroRoute, ...grouhKhodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GrouhKhodroComponent,
        GrouhKhodroDetailComponent,
        GrouhKhodroUpdateComponent,
        GrouhKhodroDeleteDialogComponent,
        GrouhKhodroDeletePopupComponent
    ],
    entryComponents: [GrouhKhodroComponent, GrouhKhodroUpdateComponent, GrouhKhodroDeleteDialogComponent, GrouhKhodroDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartGrouhKhodroModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
