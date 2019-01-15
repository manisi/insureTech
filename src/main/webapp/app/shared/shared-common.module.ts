import { NgModule } from '@angular/core';

import { InsurancestartSharedLibsModule, FindLanguageFromKeyPipe, InsutechAlertComponent, InsutechAlertErrorComponent } from './';

@NgModule({
    imports: [InsurancestartSharedLibsModule],
    declarations: [FindLanguageFromKeyPipe, InsutechAlertComponent, InsutechAlertErrorComponent],
    exports: [InsurancestartSharedLibsModule, FindLanguageFromKeyPipe, InsutechAlertComponent, InsutechAlertErrorComponent]
})
export class InsurancestartSharedCommonModule {}
