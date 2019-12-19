/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratSalesMaliUpdateComponent } from 'app/entities/adam-khesarat-sales-mali/adam-khesarat-sales-mali-update.component';
import { AdamKhesaratSalesMaliService } from 'app/entities/adam-khesarat-sales-mali/adam-khesarat-sales-mali.service';
import { AdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';

describe('Component Tests', () => {
    describe('AdamKhesaratSalesMali Management Update Component', () => {
        let comp: AdamKhesaratSalesMaliUpdateComponent;
        let fixture: ComponentFixture<AdamKhesaratSalesMaliUpdateComponent>;
        let service: AdamKhesaratSalesMaliService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratSalesMaliUpdateComponent]
            })
                .overrideTemplate(AdamKhesaratSalesMaliUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdamKhesaratSalesMaliUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdamKhesaratSalesMaliService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdamKhesaratSalesMali(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adamKhesaratSalesMali = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdamKhesaratSalesMali();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adamKhesaratSalesMali = entity;
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
