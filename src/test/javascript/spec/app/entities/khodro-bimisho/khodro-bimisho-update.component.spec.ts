/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhodroBimishoUpdateComponent } from 'app/entities/khodro-bimisho/khodro-bimisho-update.component';
import { KhodroBimishoService } from 'app/entities/khodro-bimisho/khodro-bimisho.service';
import { KhodroBimisho } from 'app/shared/model/khodro-bimisho.model';

describe('Component Tests', () => {
    describe('KhodroBimisho Management Update Component', () => {
        let comp: KhodroBimishoUpdateComponent;
        let fixture: ComponentFixture<KhodroBimishoUpdateComponent>;
        let service: KhodroBimishoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhodroBimishoUpdateComponent]
            })
                .overrideTemplate(KhodroBimishoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KhodroBimishoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhodroBimishoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhodroBimisho(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khodro = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new KhodroBimisho();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.khodro = entity;
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
