import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    KhesaratSrneshinComponent,
    KhesaratSrneshinDetailComponent,
    KhesaratSrneshinUpdateComponent,
    KhesaratSrneshinDeletePopupComponent,
    KhesaratSrneshinDeleteDialogComponent,
    khesaratSrneshinRoute,
    khesaratSrneshinPopupRoute
} from './';

const ENTITY_STATES = [...khesaratSrneshinRoute, ...khesaratSrneshinPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KhesaratSrneshinComponent,
        KhesaratSrneshinDetailComponent,
        KhesaratSrneshinUpdateComponent,
        KhesaratSrneshinDeleteDialogComponent,
        KhesaratSrneshinDeletePopupComponent
    ],
    entryComponents: [
        KhesaratSrneshinComponent,
        KhesaratSrneshinUpdateComponent,
        KhesaratSrneshinDeleteDialogComponent,
        KhesaratSrneshinDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKhesaratSrneshinModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
