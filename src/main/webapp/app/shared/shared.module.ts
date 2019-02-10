import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbCalendar, NgbCalendarPersian, NgbDateAdapter, NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { InsurancestartSharedLibsModule, InsurancestartSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';
import { NgbDatepickerI18nPersian } from './util/datepicker-i18persian';

@NgModule({
    imports: [InsurancestartSharedLibsModule, InsurancestartSharedCommonModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
    providers: [
        { provide: NgbDateAdapter, useClass: NgbDateMomentAdapter },
        { provide: NgbCalendar, useClass: NgbCalendarPersian },
        { provide: NgbDatepickerI18n, useClass: NgbDatepickerI18nPersian }
    ],
    entryComponents: [JhiLoginModalComponent],
    exports: [InsurancestartSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSharedModule {
    static forRoot() {
        return {
            ngModule: InsurancestartSharedModule
        };
    }
}
