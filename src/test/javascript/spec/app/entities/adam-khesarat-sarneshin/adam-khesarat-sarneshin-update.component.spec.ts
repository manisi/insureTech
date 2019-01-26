/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratSarneshinUpdateComponent } from 'app/entities/adam-khesarat-sarneshin/adam-khesarat-sarneshin-update.component';
import { AdamKhesaratSarneshinService } from 'app/entities/adam-khesarat-sarneshin/adam-khesarat-sarneshin.service';
import { AdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';

describe('Component Tests', () => {
    describe('AdamKhesaratSarneshin Management Update Component', () => {
        let comp: AdamKhesaratSarneshinUpdateComponent;
        let fixture: ComponentFixture<AdamKhesaratSarneshinUpdateComponent>;
        let service: AdamKhesaratSarneshinService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratSarneshinUpdateComponent]
            })
                .overrideTemplate(AdamKhesaratSarneshinUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdamKhesaratSarneshinUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdamKhesaratSarneshinService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdamKhesaratSarneshin(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adamKhesaratSarneshin = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdamKhesaratSarneshin();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adamKhesaratSarneshin = entity;
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
