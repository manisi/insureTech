/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesJaniCalcDetailComponent } from 'app/entities/sales-jani-calc/sales-jani-calc-detail.component';
import { SalesJaniCalc } from 'app/shared/model/sales-jani-calc.model';

describe('Component Tests', () => {
    describe('SalesJaniCalc Management Detail Component', () => {
        let comp: SalesJaniCalcDetailComponent;
        let fixture: ComponentFixture<SalesJaniCalcDetailComponent>;
        const route = ({ data: of({ salesJaniCalc: new SalesJaniCalc(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesJaniCalcDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SalesJaniCalcDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SalesJaniCalcDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.salesJaniCalc).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
