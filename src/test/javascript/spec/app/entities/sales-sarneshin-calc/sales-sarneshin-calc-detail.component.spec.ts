/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesSarneshinCalcDetailComponent } from 'app/entities/sales-sarneshin-calc/sales-sarneshin-calc-detail.component';
import { SalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';

describe('Component Tests', () => {
    describe('SalesSarneshinCalc Management Detail Component', () => {
        let comp: SalesSarneshinCalcDetailComponent;
        let fixture: ComponentFixture<SalesSarneshinCalcDetailComponent>;
        const route = ({ data: of({ salesSarneshinCalc: new SalesSarneshinCalc(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesSarneshinCalcDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SalesSarneshinCalcDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SalesSarneshinCalcDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.salesSarneshinCalc).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
