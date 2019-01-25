import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    KhodroComponent,
    KhodroDetailComponent,
    KhodroUpdateComponent,
    KhodroDeletePopupComponent,
    KhodroDeleteDialogComponent,
    khodroRoute,
    khodroPopupRoute
} from './';

const ENTITY_STATES = [...khodroRoute, ...khodroPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [KhodroComponent, KhodroDetailComponent, KhodroUpdateComponent, KhodroDeleteDialogComponent, KhodroDeletePopupComponent],
    entryComponents: [KhodroComponent, KhodroUpdateComponent, KhodroDeleteDialogComponent, KhodroDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKhodroModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
