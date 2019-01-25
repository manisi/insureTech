import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    NoeSabegheComponent,
    NoeSabegheDetailComponent,
    NoeSabegheUpdateComponent,
    NoeSabegheDeletePopupComponent,
    NoeSabegheDeleteDialogComponent,
    noeSabegheRoute,
    noeSabeghePopupRoute
} from './';

const ENTITY_STATES = [...noeSabegheRoute, ...noeSabeghePopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NoeSabegheComponent,
        NoeSabegheDetailComponent,
        NoeSabegheUpdateComponent,
        NoeSabegheDeleteDialogComponent,
        NoeSabegheDeletePopupComponent
    ],
    entryComponents: [NoeSabegheComponent, NoeSabegheUpdateComponent, NoeSabegheDeleteDialogComponent, NoeSabegheDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartNoeSabegheModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
