import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    SabegheComponent,
    SabegheDetailComponent,
    SabegheUpdateComponent,
    SabegheDeletePopupComponent,
    SabegheDeleteDialogComponent,
    sabegheRoute,
    sabeghePopupRoute
} from './';

const ENTITY_STATES = [...sabegheRoute, ...sabeghePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SabegheComponent,
        SabegheDetailComponent,
        SabegheUpdateComponent,
        SabegheDeleteDialogComponent,
        SabegheDeletePopupComponent
    ],
    entryComponents: [SabegheComponent, SabegheUpdateComponent, SabegheDeleteDialogComponent, SabegheDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSabegheModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
