import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    AnvaeKhodroComponent,
    AnvaeKhodroDetailComponent,
    AnvaeKhodroUpdateComponent,
    AnvaeKhodroDeletePopupComponent,
    AnvaeKhodroDeleteDialogComponent,
    anvaeKhodroRoute,
    anvaeKhodroPopupRoute
} from './';

const ENTITY_STATES = [...anvaeKhodroRoute, ...anvaeKhodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AnvaeKhodroComponent,
        AnvaeKhodroDetailComponent,
        AnvaeKhodroUpdateComponent,
        AnvaeKhodroDeleteDialogComponent,
        AnvaeKhodroDeletePopupComponent
    ],
    entryComponents: [AnvaeKhodroComponent, AnvaeKhodroUpdateComponent, AnvaeKhodroDeleteDialogComponent, AnvaeKhodroDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartAnvaeKhodroModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
