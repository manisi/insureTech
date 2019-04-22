/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesMazadCalcDetailComponent } from 'app/entities/sales-mazad-calc/sales-mazad-calc-detail.component';
import { SalesMazadCalc } from 'app/shared/model/sales-mazad-calc.model';

describe('Component Tests', () => {
    describe('SalesMazadCalc Management Detail Component', () => {
        let comp: SalesMazadCalcDetailComponent;
        let fixture: ComponentFixture<SalesMazadCalcDetailComponent>;
        const route = ({ data: of({ salesMazadCalc: new SalesMazadCalc(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesMazadCalcDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SalesMazadCalcDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SalesMazadCalcDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.salesMazadCalc).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
