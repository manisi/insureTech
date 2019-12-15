/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { ModateBimenameUpdateComponent } from 'app/entities/modate-bimename/modate-bimename-update.component';
import { ModateBimenameService } from 'app/entities/modate-bimename/modate-bimename.service';
import { ModateBimename } from 'app/shared/model/modate-bimename.model';

describe('Component Tests', () => {
    describe('ModateBimename Management Update Component', () => {
        let comp: ModateBimenameUpdateComponent;
        let fixture: ComponentFixture<ModateBimenameUpdateComponent>;
        let service: ModateBimenameService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [ModateBimenameUpdateComponent]
            })
                .overrideTemplate(ModateBimenameUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ModateBimenameUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ModateBimenameService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ModateBimename(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.modateBimename = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ModateBimename();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.modateBimename = entity;
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
