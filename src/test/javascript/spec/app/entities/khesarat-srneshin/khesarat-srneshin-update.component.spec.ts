/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSrneshinUpdateComponent } from 'app/entities/khesarat-srneshin/khesarat-srneshin-update.component';
import { KhesaratSrneshinService } from 'app/entities/khesarat-srneshin/khesarat-srneshin.service';
import { KhesaratSrneshin } from 'app/shared/model/khesarat-srneshin.model';

describe('Component Tests', () => {
    describe('KhesaratSrneshin Management Update Component', () => {
        let comp: KhesaratSrneshinUpdateComponent;
        let fixture: ComponentFixture<KhesaratSrneshinUpdateComponent>;
        let service: KhesaratSrneshinService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSrneshinUpdateComponent]
            })
                .overrideTemplate(KhesaratSrneshinUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KhesaratSrneshinUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhesaratSrneshinService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhesaratSrneshin(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khesaratSrneshin = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhesaratSrneshin();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khesaratSrneshin = entity;
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
