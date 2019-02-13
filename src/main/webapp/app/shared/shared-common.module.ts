import { NgModule } from '@angular/core';

import { InsurancestartSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, JalaliPipe } from './';

@NgModule({
    imports: [InsurancestartSharedLibsModule],
    declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, JalaliPipe],
    exports: [InsurancestartSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, JalaliPipe]
})
export class InsurancestartSharedCommonModule {}
