import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    VaziatBimeComponent,
    VaziatBimeDetailComponent,
    VaziatBimeUpdateComponent,
    VaziatBimeDeletePopupComponent,
    VaziatBimeDeleteDialogComponent,
    vaziatBimeRoute,
    vaziatBimePopupRoute
} from './';

const ENTITY_STATES = [...vaziatBimeRoute, ...vaziatBimePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VaziatBimeComponent,
        VaziatBimeDetailComponent,
        VaziatBimeUpdateComponent,
        VaziatBimeDeleteDialogComponent,
        VaziatBimeDeletePopupComponent
    ],
    entryComponents: [VaziatBimeComponent, VaziatBimeUpdateComponent, VaziatBimeDeleteDialogComponent, VaziatBimeDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartVaziatBimeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
