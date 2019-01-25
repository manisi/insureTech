import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SherkatBimeComponent,
    SherkatBimeDetailComponent,
    SherkatBimeUpdateComponent,
    SherkatBimeDeletePopupComponent,
    SherkatBimeDeleteDialogComponent,
    sherkatBimeRoute,
    sherkatBimePopupRoute
} from './';

const ENTITY_STATES = [...sherkatBimeRoute, ...sherkatBimePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SherkatBimeComponent,
        SherkatBimeDetailComponent,
        SherkatBimeUpdateComponent,
        SherkatBimeDeleteDialogComponent,
        SherkatBimeDeletePopupComponent
    ],
    entryComponents: [SherkatBimeComponent, SherkatBimeUpdateComponent, SherkatBimeDeleteDialogComponent, SherkatBimeDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSherkatBimeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
