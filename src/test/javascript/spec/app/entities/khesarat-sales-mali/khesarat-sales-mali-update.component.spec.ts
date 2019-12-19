/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSalesMaliUpdateComponent } from 'app/entities/khesarat-sales-mali/khesarat-sales-mali-update.component';
import { KhesaratSalesMaliService } from 'app/entities/khesarat-sales-mali/khesarat-sales-mali.service';
import { KhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';

describe('Component Tests', () => {
    describe('KhesaratSalesMali Management Update Component', () => {
        let comp: KhesaratSalesMaliUpdateComponent;
        let fixture: ComponentFixture<KhesaratSalesMaliUpdateComponent>;
        let service: KhesaratSalesMaliService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSalesMaliUpdateComponent]
            })
                .overrideTemplate(KhesaratSalesMaliUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KhesaratSalesMaliUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhesaratSalesMaliService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhesaratSalesMali(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khesaratSalesMali = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhesaratSalesMali();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khesaratSalesMali = entity;
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
