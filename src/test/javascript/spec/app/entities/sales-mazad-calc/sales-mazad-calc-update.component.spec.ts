/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesMazadCalcUpdateComponent } from 'app/entities/sales-mazad-calc/sales-mazad-calc-update.component';
import { SalesMazadCalcService } from 'app/entities/sales-mazad-calc/sales-mazad-calc.service';
import { SalesMazadCalc } from 'app/shared/model/sales-mazad-calc.model';

describe('Component Tests', () => {
    describe('SalesMazadCalc Management Update Component', () => {
        let comp: SalesMazadCalcUpdateComponent;
        let fixture: ComponentFixture<SalesMazadCalcUpdateComponent>;
        let service: SalesMazadCalcService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesMazadCalcUpdateComponent]
            })
                .overrideTemplate(SalesMazadCalcUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SalesMazadCalcUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SalesMazadCalcService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SalesMazadCalc(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.salesMazadCalc = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SalesMazadCalc();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.salesMazadCalc = entity;
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
