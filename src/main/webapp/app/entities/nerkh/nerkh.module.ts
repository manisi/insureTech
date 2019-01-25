import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    NerkhComponent,
    NerkhDetailComponent,
    NerkhUpdateComponent,
    NerkhDeletePopupComponent,
    NerkhDeleteDialogComponent,
    nerkhRoute,
    nerkhPopupRoute
} from './';

const ENTITY_STATES = [...nerkhRoute, ...nerkhPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [NerkhComponent, NerkhDetailComponent, NerkhUpdateComponent, NerkhDeleteDialogComponent, NerkhDeletePopupComponent],
    entryComponents: [NerkhComponent, NerkhUpdateComponent, NerkhDeleteDialogComponent, NerkhDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartNerkhModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
