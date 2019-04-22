/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesSarneshinCalcUpdateComponent } from 'app/entities/sales-sarneshin-calc/sales-sarneshin-calc-update.component';
import { SalesSarneshinCalcService } from 'app/entities/sales-sarneshin-calc/sales-sarneshin-calc.service';
import { SalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';

describe('Component Tests', () => {
    describe('SalesSarneshinCalc Management Update Component', () => {
        let comp: SalesSarneshinCalcUpdateComponent;
        let fixture: ComponentFixture<SalesSarneshinCalcUpdateComponent>;
        let service: SalesSarneshinCalcService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesSarneshinCalcUpdateComponent]
            })
                .overrideTemplate(SalesSarneshinCalcUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SalesSarneshinCalcUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SalesSarneshinCalcService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SalesSarneshinCalc(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.salesSarneshinCalc = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SalesSarneshinCalc();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.salesSarneshinCalc = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
