/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AshkhasUpdateComponent } from 'app/entities/ashkhas/ashkhas-update.component';
import { AshkhasService } from 'app/entities/ashkhas/ashkhas.service';
import { Ashkhas } from 'app/shared/model/ashkhas.model';

describe('Component Tests', () => {
    describe('Ashkhas Management Update Component', () => {
        let comp: AshkhasUpdateComponent;
        let fixture: ComponentFixture<AshkhasUpdateComponent>;
        let service: AshkhasService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AshkhasUpdateComponent]
            })
                .overrideTemplate(AshkhasUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AshkhasUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AshkhasService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Ashkhas(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ashkhas = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Ashkhas();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ashkhas = entity;
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
