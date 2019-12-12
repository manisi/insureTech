import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsurancestartSharedModule } from '../shared';

import { HI_THERE_ROUTE, HiThereComponent } from './';
import { PageOneComponent } from './page-one/page-one.component';
import { PageTwoComponent } from './page-two/page-two.component';

@NgModule({
    imports: [InsurancestartSharedModule, RouterModule.forRoot([HI_THERE_ROUTE], { useHash: true })],

    declarations: [HiThereComponent, PageOneComponent, PageTwoComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartAppHiThereModule {}
