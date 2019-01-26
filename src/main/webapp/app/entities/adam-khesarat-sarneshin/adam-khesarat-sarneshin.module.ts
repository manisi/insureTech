import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    AdamKhesaratSarneshinComponent,
    AdamKhesaratSarneshinDetailComponent,
    AdamKhesaratSarneshinUpdateComponent,
    AdamKhesaratSarneshinDeletePopupComponent,
    AdamKhesaratSarneshinDeleteDialogComponent,
    adamKhesaratSarneshinRoute,
    adamKhesaratSarneshinPopupRoute
} from './';

const ENTITY_STATES = [...adamKhesaratSarneshinRoute, ...adamKhesaratSarneshinPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdamKhesaratSarneshinComponent,
        AdamKhesaratSarneshinDetailComponent,
        AdamKhesaratSarneshinUpdateComponent,
        AdamKhesaratSarneshinDeleteDialogComponent,
        AdamKhesaratSarneshinDeletePopupComponent
    ],
    entryComponents: [
        AdamKhesaratSarneshinComponent,
        AdamKhesaratSarneshinUpdateComponent,
        AdamKhesaratSarneshinDeleteDialogComponent,
        AdamKhesaratSarneshinDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartAdamKhesaratSarneshinModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
