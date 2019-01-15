import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import {
    InsurancestartSharedLibsModule,
    InsurancestartSharedCommonModule,
    InsutechLoginModalComponent,
    HasAnyAuthorityDirective
} from './';

@NgModule({
    imports: [InsurancestartSharedLibsModule, InsurancestartSharedCommonModule],
    declarations: [InsutechLoginModalComponent, HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [InsutechLoginModalComponent],
    exports: [InsurancestartSharedCommonModule, InsutechLoginModalComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartSharedModule {
    static forRoot() {
        return {
            ngModule: InsurancestartSharedModule
        };
    }
}
