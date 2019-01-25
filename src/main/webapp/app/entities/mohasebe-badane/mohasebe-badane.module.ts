import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    MohasebeBadaneComponent,
    MohasebeBadaneDetailComponent,
    MohasebeBadaneUpdateComponent,
    MohasebeBadaneDeletePopupComponent,
    MohasebeBadaneDeleteDialogComponent,
    mohasebeBadaneRoute,
    mohasebeBadanePopupRoute
} from './';

const ENTITY_STATES = [...mohasebeBadaneRoute, ...mohasebeBadanePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MohasebeBadaneComponent,
        MohasebeBadaneDetailComponent,
        MohasebeBadaneUpdateComponent,
        MohasebeBadaneDeleteDialogComponent,
        MohasebeBadaneDeletePopupComponent
    ],
    entryComponents: [
        MohasebeBadaneComponent,
        MohasebeBadaneUpdateComponent,
        MohasebeBadaneDeleteDialogComponent,
        MohasebeBadaneDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartMohasebeBadaneModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
