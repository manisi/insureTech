import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SabegheKhesaratComponent,
    SabegheKhesaratDetailComponent,
    SabegheKhesaratUpdateComponent,
    SabegheKhesaratDeletePopupComponent,
    SabegheKhesaratDeleteDialogComponent,
    sabegheKhesaratRoute,
    sabegheKhesaratPopupRoute
} from './';

const ENTITY_STATES = [...sabegheKhesaratRoute, ...sabegheKhesaratPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SabegheKhesaratComponent,
        SabegheKhesaratDetailComponent,
        SabegheKhesaratUpdateComponent,
        SabegheKhesaratDeleteDialogComponent,
        SabegheKhesaratDeletePopupComponent
    ],
    entryComponents: [
        SabegheKhesaratComponent,
        SabegheKhesaratUpdateComponent,
        SabegheKhesaratDeleteDialogComponent,
        SabegheKhesaratDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSabegheKhesaratModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
