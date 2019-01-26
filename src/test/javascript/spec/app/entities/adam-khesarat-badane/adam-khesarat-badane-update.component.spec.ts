/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratBadaneUpdateComponent } from 'app/entities/adam-khesarat-badane/adam-khesarat-badane-update.component';
import { AdamKhesaratBadaneService } from 'app/entities/adam-khesarat-badane/adam-khesarat-badane.service';
import { AdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';

describe('Component Tests', () => {
    describe('AdamKhesaratBadane Management Update Component', () => {
        let comp: AdamKhesaratBadaneUpdateComponent;
        let fixture: ComponentFixture<AdamKhesaratBadaneUpdateComponent>;
        let service: AdamKhesaratBadaneService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratBadaneUpdateComponent]
            })
                .overrideTemplate(AdamKhesaratBadaneUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdamKhesaratBadaneUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdamKhesaratBadaneService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdamKhesaratBadane(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adamKhesaratBadane = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdamKhesaratBadane();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adamKhesaratBadane = entity;
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
