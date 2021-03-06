import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    KohnegiComponent,
    KohnegiDetailComponent,
    KohnegiUpdateComponent,
    KohnegiDeletePopupComponent,
    KohnegiDeleteDialogComponent,
    kohnegiRoute,
    kohnegiPopupRoute
} from './';

const ENTITY_STATES = [...kohnegiRoute, ...kohnegiPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KohnegiComponent,
        KohnegiDetailComponent,
        KohnegiUpdateComponent,
        KohnegiDeleteDialogComponent,
        KohnegiDeletePopupComponent
    ],
    entryComponents: [KohnegiComponent, KohnegiUpdateComponent, KohnegiDeleteDialogComponent, KohnegiDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartKohnegiModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
