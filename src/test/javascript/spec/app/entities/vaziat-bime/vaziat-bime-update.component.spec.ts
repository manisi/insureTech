/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { VaziatBimeUpdateComponent } from 'app/entities/vaziat-bime/vaziat-bime-update.component';
import { VaziatBimeService } from 'app/entities/vaziat-bime/vaziat-bime.service';
import { VaziatBime } from 'app/shared/model/vaziat-bime.model';

describe('Component Tests', () => {
    describe('VaziatBime Management Update Component', () => {
        let comp: VaziatBimeUpdateComponent;
        let fixture: ComponentFixture<VaziatBimeUpdateComponent>;
        let service: VaziatBimeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [VaziatBimeUpdateComponent]
            })
                .overrideTemplate(VaziatBimeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VaziatBimeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VaziatBimeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new VaziatBime(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vaziatBime = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new VaziatBime();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vaziatBime = entity;
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
