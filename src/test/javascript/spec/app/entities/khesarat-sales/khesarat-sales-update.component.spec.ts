/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSalesUpdateComponent } from 'app/entities/khesarat-sales/khesarat-sales-update.component';
import { KhesaratSalesService } from 'app/entities/khesarat-sales/khesarat-sales.service';
import { KhesaratSales } from 'app/shared/model/khesarat-sales.model';

describe('Component Tests', () => {
    describe('KhesaratSales Management Update Component', () => {
        let comp: KhesaratSalesUpdateComponent;
        let fixture: ComponentFixture<KhesaratSalesUpdateComponent>;
        let service: KhesaratSalesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSalesUpdateComponent]
            })
                .overrideTemplate(KhesaratSalesUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KhesaratSalesUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhesaratSalesService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhesaratSales(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khesaratSales = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhesaratSales();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khesaratSales = entity;
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
