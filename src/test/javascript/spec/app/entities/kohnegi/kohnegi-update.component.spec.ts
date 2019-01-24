/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KohnegiUpdateComponent } from 'app/entities/kohnegi/kohnegi-update.component';
import { KohnegiService } from 'app/entities/kohnegi/kohnegi.service';
import { Kohnegi } from 'app/shared/model/kohnegi.model';

describe('Component Tests', () => {
    describe('Kohnegi Management Update Component', () => {
        let comp: KohnegiUpdateComponent;
        let fixture: ComponentFixture<KohnegiUpdateComponent>;
        let service: KohnegiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KohnegiUpdateComponent]
            })
                .overrideTemplate(KohnegiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KohnegiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KohnegiService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Kohnegi(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.kohnegi = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Kohnegi();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.kohnegi = entity;
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
