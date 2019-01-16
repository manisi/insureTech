import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { InsurancestartAshkhasBimishoModule } from './ashkhas-bimisho/ashkhas-bimisho.module';
import { InsurancestartPoosheshBimishoModule } from './pooshesh-bimisho/pooshesh-bimisho.module';
import { InsurancestartKhodroBimishoModule } from './khodro-bimisho/khodro-bimisho.module';
import { InsurancestartTipKhodroBimishoModule } from './tip-khodro-bimisho/tip-khodro-bimisho.module';
import { InsurancestartNerkhBimishoModule } from './nerkh-bimisho/nerkh-bimisho.module';
import { InsurancestartSherkatBimeBimishoModule } from './sherkat-bime-bimisho/sherkat-bime-bimisho.module';
import { InsurancestartCityBimishoModule } from './city-bimisho/city-bimisho.module';
import { InsurancestartCountryBimishoModule } from './country-bimisho/country-bimisho.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        InsurancestartAshkhasBimishoModule,
        InsurancestartPoosheshBimishoModule,
        InsurancestartKhodroBimishoModule,
        InsurancestartTipKhodroBimishoModule,
        InsurancestartNerkhBimishoModule,
        InsurancestartSherkatBimeBimishoModule,
        InsurancestartCityBimishoModule,
        InsurancestartCountryBimishoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartEntityModule {}
