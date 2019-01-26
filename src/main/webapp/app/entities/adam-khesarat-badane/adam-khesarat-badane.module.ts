import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    AdamKhesaratBadaneComponent,
    AdamKhesaratBadaneDetailComponent,
    AdamKhesaratBadaneUpdateComponent,
    AdamKhesaratBadaneDeletePopupComponent,
    AdamKhesaratBadaneDeleteDialogComponent,
    adamKhesaratBadaneRoute,
    adamKhesaratBadanePopupRoute
} from './';

const ENTITY_STATES = [...adamKhesaratBadaneRoute, ...adamKhesaratBadanePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdamKhesaratBadaneComponent,
        AdamKhesaratBadaneDetailComponent,
        AdamKhesaratBadaneUpdateComponent,
        AdamKhesaratBadaneDeleteDialogComponent,
        AdamKhesaratBadaneDeletePopupComponent
    ],
    entryComponents: [
        AdamKhesaratBadaneComponent,
        AdamKhesaratBadaneUpdateComponent,
        AdamKhesaratBadaneDeleteDialogComponent,
        AdamKhesaratBadaneDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartAdamKhesaratBadaneModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
