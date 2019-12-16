import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    TakhfifTavafoghiComponent,
    TakhfifTavafoghiDetailComponent,
    TakhfifTavafoghiUpdateComponent,
    TakhfifTavafoghiDeletePopupComponent,
    TakhfifTavafoghiDeleteDialogComponent,
    takhfifTavafoghiRoute,
    takhfifTavafoghiPopupRoute
} from './';

const ENTITY_STATES = [...takhfifTavafoghiRoute, ...takhfifTavafoghiPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TakhfifTavafoghiComponent,
        TakhfifTavafoghiDetailComponent,
        TakhfifTavafoghiUpdateComponent,
        TakhfifTavafoghiDeleteDialogComponent,
        TakhfifTavafoghiDeletePopupComponent
    ],
    entryComponents: [
        TakhfifTavafoghiComponent,
        TakhfifTavafoghiUpdateComponent,
        TakhfifTavafoghiDeleteDialogComponent,
        TakhfifTavafoghiDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartTakhfifTavafoghiModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
