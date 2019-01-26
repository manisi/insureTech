import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    KohnegiBadaneComponent,
    KohnegiBadaneDetailComponent,
    KohnegiBadaneUpdateComponent,
    KohnegiBadaneDeletePopupComponent,
    KohnegiBadaneDeleteDialogComponent,
    kohnegiBadaneRoute,
    kohnegiBadanePopupRoute
} from './';

const ENTITY_STATES = [...kohnegiBadaneRoute, ...kohnegiBadanePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KohnegiBadaneComponent,
        KohnegiBadaneDetailComponent,
        KohnegiBadaneUpdateComponent,
        KohnegiBadaneDeleteDialogComponent,
        KohnegiBadaneDeletePopupComponent
    ],
    entryComponents: [
        KohnegiBadaneComponent,
        KohnegiBadaneUpdateComponent,
        KohnegiBadaneDeleteDialogComponent,
        KohnegiBadaneDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKohnegiBadaneModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
