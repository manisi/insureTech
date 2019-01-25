import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    JarimeDirkardComponent,
    JarimeDirkardDetailComponent,
    JarimeDirkardUpdateComponent,
    JarimeDirkardDeletePopupComponent,
    JarimeDirkardDeleteDialogComponent,
    jarimeDirkardRoute,
    jarimeDirkardPopupRoute
} from './';

const ENTITY_STATES = [...jarimeDirkardRoute, ...jarimeDirkardPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JarimeDirkardComponent,
        JarimeDirkardDetailComponent,
        JarimeDirkardUpdateComponent,
        JarimeDirkardDeleteDialogComponent,
        JarimeDirkardDeletePopupComponent
    ],
    entryComponents: [
        JarimeDirkardComponent,
        JarimeDirkardUpdateComponent,
        JarimeDirkardDeleteDialogComponent,
        JarimeDirkardDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartJarimeDirkardModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
