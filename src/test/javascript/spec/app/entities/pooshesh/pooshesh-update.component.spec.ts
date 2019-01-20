/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { PoosheshUpdateComponent } from 'app/entities/pooshesh/pooshesh-update.component';
import { PoosheshService } from 'app/entities/pooshesh/pooshesh.service';
import { Pooshesh } from 'app/shared/model/pooshesh.model';

describe('Component Tests', () => {
    describe('Pooshesh Management Update Component', () => {
        let comp: PoosheshUpdateComponent;
        let fixture: ComponentFixture<PoosheshUpdateComponent>;
        let service: PoosheshService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [PoosheshUpdateComponent]
            })
                .overrideTemplate(PoosheshUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PoosheshUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PoosheshService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Pooshesh(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pooshesh = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Pooshesh();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pooshesh = entity;
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
