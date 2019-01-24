/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { GrouhKhodroUpdateComponent } from 'app/entities/grouh-khodro/grouh-khodro-update.component';
import { GrouhKhodroService } from 'app/entities/grouh-khodro/grouh-khodro.service';
import { GrouhKhodro } from 'app/shared/model/grouh-khodro.model';

describe('Component Tests', () => {
    describe('GrouhKhodro Management Update Component', () => {
        let comp: GrouhKhodroUpdateComponent;
        let fixture: ComponentFixture<GrouhKhodroUpdateComponent>;
        let service: GrouhKhodroService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [GrouhKhodroUpdateComponent]
            })
                .overrideTemplate(GrouhKhodroUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GrouhKhodroUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GrouhKhodroService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new GrouhKhodro(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.grouhKhodro = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new GrouhKhodro();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.grouhKhodro = entity;
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
