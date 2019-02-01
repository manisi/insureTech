/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AnvaeKhodroUpdateComponent } from 'app/entities/anvae-khodro/anvae-khodro-update.component';
import { AnvaeKhodroService } from 'app/entities/anvae-khodro/anvae-khodro.service';
import { AnvaeKhodro } from 'app/shared/model/anvae-khodro.model';

describe('Component Tests', () => {
    describe('AnvaeKhodro Management Update Component', () => {
        let comp: AnvaeKhodroUpdateComponent;
        let fixture: ComponentFixture<AnvaeKhodroUpdateComponent>;
        let service: AnvaeKhodroService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AnvaeKhodroUpdateComponent]
            })
                .overrideTemplate(AnvaeKhodroUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AnvaeKhodroUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AnvaeKhodroService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AnvaeKhodro(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.anvaeKhodro = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AnvaeKhodro();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.anvaeKhodro = entity;
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
