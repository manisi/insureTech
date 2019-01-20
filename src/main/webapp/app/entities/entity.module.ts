import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { InsurancestartAshkhasModule } from './ashkhas/ashkhas.module';
import { InsurancestartPoosheshModule } from './pooshesh/pooshesh.module';
import { InsurancestartKhodroModule } from './khodro/khodro.module';
import { InsurancestartTipKhodroModule } from './tip-khodro/tip-khodro.module';
import { InsurancestartNerkhModule } from './nerkh/nerkh.module';
import { InsurancestartSherkatBimeModule } from './sherkat-bime/sherkat-bime.module';
import { InsurancestartCityModule } from './city/city.module';
import { InsurancestartCountryModule } from './country/country.module';
import { InsurancestartMohasebeSalesModule } from './mohasebe-sales/mohasebe-sales.module';
import { InsurancestartMohasebeBadaneModule } from './mohasebe-badane/mohasebe-badane.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        InsurancestartAshkhasModule,
        InsurancestartPoosheshModule,
        InsurancestartKhodroModule,
        InsurancestartTipKhodroModule,
        InsurancestartNerkhModule,
        InsurancestartSherkatBimeModule,
        InsurancestartCityModule,
        InsurancestartCountryModule,
        InsurancestartMohasebeSalesModule,
        InsurancestartMohasebeBadaneModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartEntityModule {}
