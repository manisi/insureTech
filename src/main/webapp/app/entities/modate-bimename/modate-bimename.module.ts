import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    ModateBimenameComponent,
    ModateBimenameDetailComponent,
    ModateBimenameUpdateComponent,
    ModateBimenameDeletePopupComponent,
    ModateBimenameDeleteDialogComponent,
    modateBimenameRoute,
    modateBimenamePopupRoute
} from './';

const ENTITY_STATES = [...modateBimenameRoute, ...modateBimenamePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ModateBimenameComponent,
        ModateBimenameDetailComponent,
        ModateBimenameUpdateComponent,
        ModateBimenameDeleteDialogComponent,
        ModateBimenameDeletePopupComponent
    ],
    entryComponents: [
        ModateBimenameComponent,
        ModateBimenameUpdateComponent,
        ModateBimenameDeleteDialogComponent,
        ModateBimenameDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartModateBimenameModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
