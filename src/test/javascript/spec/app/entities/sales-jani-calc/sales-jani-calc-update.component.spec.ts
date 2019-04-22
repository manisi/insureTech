/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesJaniCalcUpdateComponent } from 'app/entities/sales-jani-calc/sales-jani-calc-update.component';
import { SalesJaniCalcService } from 'app/entities/sales-jani-calc/sales-jani-calc.service';
import { SalesJaniCalc } from 'app/shared/model/sales-jani-calc.model';

describe('Component Tests', () => {
    describe('SalesJaniCalc Management Update Component', () => {
        let comp: SalesJaniCalcUpdateComponent;
        let fixture: ComponentFixture<SalesJaniCalcUpdateComponent>;
        let service: SalesJaniCalcService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesJaniCalcUpdateComponent]
            })
                .overrideTemplate(SalesJaniCalcUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SalesJaniCalcUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SalesJaniCalcService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SalesJaniCalc(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.salesJaniCalc = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SalesJaniCalc();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.salesJaniCalc = entity;
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
