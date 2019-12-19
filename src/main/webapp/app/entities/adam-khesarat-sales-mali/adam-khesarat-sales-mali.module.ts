import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    AdamKhesaratSalesMaliComponent,
    AdamKhesaratSalesMaliDetailComponent,
    AdamKhesaratSalesMaliUpdateComponent,
    AdamKhesaratSalesMaliDeletePopupComponent,
    AdamKhesaratSalesMaliDeleteDialogComponent,
    adamKhesaratSalesMaliRoute,
    adamKhesaratSalesMaliPopupRoute
} from './';

const ENTITY_STATES = [...adamKhesaratSalesMaliRoute, ...adamKhesaratSalesMaliPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdamKhesaratSalesMaliComponent,
        AdamKhesaratSalesMaliDetailComponent,
        AdamKhesaratSalesMaliUpdateComponent,
        AdamKhesaratSalesMaliDeleteDialogComponent,
        AdamKhesaratSalesMaliDeletePopupComponent
    ],
    entryComponents: [
        AdamKhesaratSalesMaliComponent,
        AdamKhesaratSalesMaliUpdateComponent,
        AdamKhesaratSalesMaliDeleteDialogComponent,
        AdamKhesaratSalesMaliDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartAdamKhesaratSalesMaliModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
