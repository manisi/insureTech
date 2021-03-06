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
            },
            {
                path: 'adam-khesarat',
                loadChildren: './adam-khesarat/adam-khesarat.module#InsurancestartAdamKhesaratModule'
            },
            {
                path: 'adam-khesarat',
                loadChildren: './adam-khesarat/adam-khesarat.module#InsurancestartAdamKhesaratModule'
            },
            {
                path: 'adam-khesarat',
                loadChildren: './adam-khesarat/adam-khesarat.module#InsurancestartAdamKhesaratModule'
            },
            {
                path: 'grouh-khodro',
                loadChildren: './grouh-khodro/grouh-khodro.module#InsurancestartGrouhKhodroModule'
            },
            {
                path: 'anvae-khodro',
                loadChildren: './anvae-khodro/anvae-khodro.module#InsurancestartAnvaeKhodroModule'
            },
            {
                path: 'anvae-khodro',
                loadChildren: './anvae-khodro/anvae-khodro.module#InsurancestartAnvaeKhodroModule'
            },
            {
                path: 'kohnegi-badane',
                loadChildren: './kohnegi-badane/kohnegi-badane.module#InsurancestartKohnegiBadaneModule'
            },
            {
                path: 'kohnegi',
                loadChildren: './kohnegi/kohnegi.module#InsurancestartKohnegiModule'
            },
            {
                path: 'anvae-khodro',
                loadChildren: './anvae-khodro/anvae-khodro.module#InsurancestartAnvaeKhodroModule'
            },
            {
                path: 'anvae-khodro',
                loadChildren: './anvae-khodro/anvae-khodro.module#InsurancestartAnvaeKhodroModule'
            },
            {
                path: 'anvae-khodro',
                loadChildren: './anvae-khodro/anvae-khodro.module#InsurancestartAnvaeKhodroModule'
            },
            {
                path: 'sabeghe',
                loadChildren: './sabeghe/sabeghe.module#InsurancestartSabegheModule'
            },
            {
                path: 'sabeghe',
                loadChildren: './sabeghe/sabeghe.module#InsurancestartSabegheModule'
            },
            {
                path: 'sabeghe',
                loadChildren: './sabeghe/sabeghe.module#InsurancestartSabegheModule'
            },
            {
                path: 'sales-jani-calc',
                loadChildren: './sales-jani-calc/sales-jani-calc.module#InsurancestartSalesJaniCalcModule'
            },
            {
                path: 'sales-sarneshin-calc',
                loadChildren: './sales-sarneshin-calc/sales-sarneshin-calc.module#InsurancestartSalesSarneshinCalcModule'
            },
            {
                path: 'sales-mazad-calc',
                loadChildren: './sales-mazad-calc/sales-mazad-calc.module#InsurancestartSalesMazadCalcModule'
            },
            {
                path: 'sales-sarneshin-calc',
                loadChildren: './sales-sarneshin-calc/sales-sarneshin-calc.module#InsurancestartSalesSarneshinCalcModule'
            },
            {
                path: 'sales-jani-calc',
                loadChildren: './sales-jani-calc/sales-jani-calc.module#InsurancestartSalesJaniCalcModule'
            },
            {
                path: 'mored-estefade-sales',
                loadChildren: './mored-estefade-sales/mored-estefade-sales.module#InsurancestartMoredEstefadeSalesModule'
            },
            {
                path: 'mored-estefade-sales',
                loadChildren: './mored-estefade-sales/mored-estefade-sales.module#InsurancestartMoredEstefadeSalesModule'
            },
            {
                path: 'onvan-khodro',
                loadChildren: './onvan-khodro/onvan-khodro.module#InsurancestartOnvanKhodroModule'
            },
            {
                path: 'mored-estefade-sales',
                loadChildren: './mored-estefade-sales/mored-estefade-sales.module#InsurancestartMoredEstefadeSalesModule'
            },
            {
                path: 'jarime-dirkard',
                loadChildren: './jarime-dirkard/jarime-dirkard.module#InsurancestartJarimeDirkardModule'
            },
            {
                path: 'kohnegi-badane',
                loadChildren: './kohnegi-badane/kohnegi-badane.module#InsurancestartKohnegiBadaneModule'
            },
            {
                path: 'kohnegi',
                loadChildren: './kohnegi/kohnegi.module#InsurancestartKohnegiModule'
            },
            {
                path: 'group-operations-data',
                loadChildren: './group-operations-data/group-operations-data.module#InsurancestartGroupOperationsDataModule'
            },
            {
                path: 'saal-sakht',
                loadChildren: './saal-sakht/saal-sakht.module#InsurancestartSaalSakhtModule'
            },
            {
                path: 'estelaam-sales-nerkh',
                loadChildren: './estelaam-sales-nerkh/estelaam-sales-nerkh.module#InsurancestartEstelaamSalesNerkhModule'
            },
            {
                path: 'estelaam-sales-nerkh',
                loadChildren: './estelaam-sales-nerkh/estelaam-sales-nerkh.module#InsurancestartEstelaamSalesNerkhModule'
            },
            {
                path: 'vaziat-bime',
                loadChildren: './vaziat-bime/vaziat-bime.module#InsurancestartVaziatBimeModule'
            },
            {
                path: 'modate-bimename',
                loadChildren: './modate-bimename/modate-bimename.module#InsurancestartModateBimenameModule'
            },
            {
                path: 'sabeghe-khesarat',
                loadChildren: './sabeghe-khesarat/sabeghe-khesarat.module#InsurancestartSabegheKhesaratModule'
            },
            {
                path: 'takhfif-tavafoghi',
                loadChildren: './takhfif-tavafoghi/takhfif-tavafoghi.module#InsurancestartTakhfifTavafoghiModule'
            },
            {
                path: 'khesarat-sales-mali',
                loadChildren: './khesarat-sales-mali/khesarat-sales-mali.module#InsurancestartKhesaratSalesMaliModule'
            },
            {
                path: 'adam-khesarat-sales-mali',
                loadChildren: './adam-khesarat-sales-mali/adam-khesarat-sales-mali.module#InsurancestartAdamKhesaratSalesMaliModule'
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
