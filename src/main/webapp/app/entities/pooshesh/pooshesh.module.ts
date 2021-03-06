import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    PoosheshComponent,
    PoosheshDetailComponent,
    PoosheshUpdateComponent,
    PoosheshDeletePopupComponent,
    PoosheshDeleteDialogComponent,
    poosheshRoute,
    poosheshPopupRoute
} from './';

const ENTITY_STATES = [...poosheshRoute, ...poosheshPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PoosheshComponent,
        PoosheshDetailComponent,
        PoosheshUpdateComponent,
        PoosheshDeleteDialogComponent,
        PoosheshDeletePopupComponent
    ],
    entryComponents: [PoosheshComponent, PoosheshUpdateComponent, PoosheshDeleteDialogComponent, PoosheshDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartPoosheshModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
