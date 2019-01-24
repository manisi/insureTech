/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratUpdateComponent } from 'app/entities/adam-khesarat/adam-khesarat-update.component';
import { AdamKhesaratService } from 'app/entities/adam-khesarat/adam-khesarat.service';
import { AdamKhesarat } from 'app/shared/model/adam-khesarat.model';

describe('Component Tests', () => {
    describe('AdamKhesarat Management Update Component', () => {
        let comp: AdamKhesaratUpdateComponent;
        let fixture: ComponentFixture<AdamKhesaratUpdateComponent>;
        let service: AdamKhesaratService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratUpdateComponent]
            })
                .overrideTemplate(AdamKhesaratUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdamKhesaratUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdamKhesaratService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdamKhesarat(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adamKhesarat = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdamKhesarat();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adamKhesarat = entity;
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
