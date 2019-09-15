import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InsurancestartSharedModule } from 'app/shared';
import {
    GroupOperationsDataComponent,
    GroupOperationsDataDetailComponent,
    GroupOperationsDataUpdateComponent,
    GroupOperationsDataDeletePopupComponent,
    GroupOperationsDataDeleteDialogComponent,
    GroupOperationsDataApplyFilePopupComponent,
    GroupOperationsDataApplyFileDialogComponent,
    groupOperationsDataRoute,
    groupOperationsDataPopupRoute
} from './';

const ENTITY_STATES = [...groupOperationsDataRoute, ...groupOperationsDataPopupRoute];

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GroupOperationsDataComponent,
        GroupOperationsDataDetailComponent,
        GroupOperationsDataUpdateComponent,
        GroupOperationsDataDeleteDialogComponent,
        GroupOperationsDataDeletePopupComponent,
        GroupOperationsDataApplyFileDialogComponent,
        GroupOperationsDataApplyFilePopupComponent
    ],
    entryComponents: [
        GroupOperationsDataComponent,
        GroupOperationsDataUpdateComponent,
        GroupOperationsDataDeleteDialogComponent,
        GroupOperationsDataDeletePopupComponent,
        GroupOperationsDataApplyFileDialogComponent,
        GroupOperationsDataApplyFilePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartGroupOperationsDataModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
