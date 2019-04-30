/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { OnvanKhodroUpdateComponent } from 'app/entities/onvan-khodro/onvan-khodro-update.component';
import { OnvanKhodroService } from 'app/entities/onvan-khodro/onvan-khodro.service';
import { OnvanKhodro } from 'app/shared/model/onvan-khodro.model';

describe('Component Tests', () => {
    describe('OnvanKhodro Management Update Component', () => {
        let comp: OnvanKhodroUpdateComponent;
        let fixture: ComponentFixture<OnvanKhodroUpdateComponent>;
        let service: OnvanKhodroService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [OnvanKhodroUpdateComponent]
            })
                .overrideTemplate(OnvanKhodroUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OnvanKhodroUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OnvanKhodroService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new OnvanKhodro(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.onvanKhodro = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new OnvanKhodro();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.onvanKhodro = entity;
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
