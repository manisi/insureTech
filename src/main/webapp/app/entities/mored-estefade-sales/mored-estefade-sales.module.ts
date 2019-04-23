import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    MoredEstefadeSalesComponent,
    MoredEstefadeSalesDetailComponent,
    MoredEstefadeSalesUpdateComponent,
    MoredEstefadeSalesDeletePopupComponent,
    MoredEstefadeSalesDeleteDialogComponent,
    moredEstefadeSalesRoute,
    moredEstefadeSalesPopupRoute
} from './';

const ENTITY_STATES = [...moredEstefadeSalesRoute, ...moredEstefadeSalesPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MoredEstefadeSalesComponent,
        MoredEstefadeSalesDetailComponent,
        MoredEstefadeSalesUpdateComponent,
        MoredEstefadeSalesDeleteDialogComponent,
        MoredEstefadeSalesDeletePopupComponent
    ],
    entryComponents: [
        MoredEstefadeSalesComponent,
        MoredEstefadeSalesUpdateComponent,
        MoredEstefadeSalesDeleteDialogComponent,
        MoredEstefadeSalesDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartMoredEstefadeSalesModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
