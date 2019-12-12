import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SaalSakhtComponent,
    SaalSakhtDetailComponent,
    SaalSakhtUpdateComponent,
    SaalSakhtDeletePopupComponent,
    SaalSakhtDeleteDialogComponent,
    saalSakhtRoute,
    saalSakhtPopupRoute
} from './';

const ENTITY_STATES = [...saalSakhtRoute, ...saalSakhtPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SaalSakhtComponent,
        SaalSakhtDetailComponent,
        SaalSakhtUpdateComponent,
        SaalSakhtDeleteDialogComponent,
        SaalSakhtDeletePopupComponent
    ],
    entryComponents: [SaalSakhtComponent, SaalSakhtUpdateComponent, SaalSakhtDeleteDialogComponent, SaalSakhtDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSaalSakhtModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
