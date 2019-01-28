import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'ashkhas',
                loadChildren: './ashkhas/ashkhas.module#InsurancestartAshkhasModule'
            },
            {
                path: 'pooshesh',
                loadChildren: './pooshesh/pooshesh.module#InsurancestartPoosheshModule'
            },
            {
                path: 'khodro',
                loadChildren: './khodro/khodro.module#InsurancestartKhodroModule'
            },
            {
                path: 'tip-khodro',
                loadChildren: './tip-khodro/tip-khodro.module#InsurancestartTipKhodroModule'
            },
            {
                path: 'nerkh',
                loadChildren: './nerkh/nerkh.module#InsurancestartNerkhModule'
            },
            {
                path: 'sherkat-bime',
                loadChildren: './sherkat-bime/sherkat-bime.module#InsurancestartSherkatBimeModule'
            },
            {
                path: 'city',
                loadChildren: './city/city.module#InsurancestartCityModule'
            },
            {
                path: 'country',
                loadChildren: './country/country.module#InsurancestartCountryModule'
            },
            {
                path: 'mohasebe-sales',
                loadChildren: './mohasebe-sales/mohasebe-sales.module#InsurancestartMohasebeSalesModule'
            },
            {
                path: 'mohasebe-badane',
                loadChildren: './mohasebe-badane/mohasebe-badane.module#InsurancestartMohasebeBadaneModule'
            },
            {
                path: 'grouh-khodro',
                loadChildren: './grouh-khodro/grouh-khodro.module#InsurancestartGrouhKhodroModule'
            },
            {
                path: 'kohnegi',
                loadChildren: './kohnegi/kohnegi.module#InsurancestartKohnegiModule'
            },
            {
                path: 'sabeghe',
                loadChildren: './sabeghe/sabeghe.module#InsurancestartSabegheModule'
            },
            {
                path: 'noe-sabeghe',
                loadChildren: './noe-sabeghe/noe-sabeghe.module#InsurancestartNoeSabegheModule'
            },
            {
                path: 'adam-khesarat',
                loadChildren: './adam-khesarat/adam-khesarat.module#InsurancestartAdamKhesaratModule'
            },
            {
                path: 'jarime-dirkard',
                loadChildren: './jarime-dirkard/jarime-dirkard.module#InsurancestartJarimeDirkardModule'
            },
            {
                path: 'khesarat-sales',
                loadChildren: './khesarat-sales/khesarat-sales.module#InsurancestartKhesaratSalesModule'
            },
            {
                path: 'kohnegi-badane',
                loadChildren: './kohnegi-badane/kohnegi-badane.module#InsurancestartKohnegiBadaneModule'
            },
            {
                path: 'khesarat-srneshin',
                loadChildren: './khesarat-srneshin/khesarat-srneshin.module#InsurancestartKhesaratSrneshinModule'
            },
            {
                path: 'adam-khesarat-sarneshin',
                loadChildren: './adam-khesarat-sarneshin/adam-khesarat-sarneshin.module#InsurancestartAdamKhesaratSarneshinModule'
            },
            {
                path: 'adam-khesarat-badane',
                loadChildren: './adam-khesarat-badane/adam-khesarat-badane.module#InsurancestartAdamKhesaratBadaneModule'
            },
            {
                path: 'adam-khesarat',
                loadChildren: './adam-khesarat/adam-khesarat.module#InsurancestartAdamKhesaratModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InsurancestartEntityModule {}
