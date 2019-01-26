/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KohnegiBadaneUpdateComponent } from 'app/entities/kohnegi-badane/kohnegi-badane-update.component';
import { KohnegiBadaneService } from 'app/entities/kohnegi-badane/kohnegi-badane.service';
import { KohnegiBadane } from 'app/shared/model/kohnegi-badane.model';

describe('Component Tests', () => {
    describe('KohnegiBadane Management Update Component', () => {
        let comp: KohnegiBadaneUpdateComponent;
        let fixture: ComponentFixture<KohnegiBadaneUpdateComponent>;
        let service: KohnegiBadaneService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KohnegiBadaneUpdateComponent]
            })
                .overrideTemplate(KohnegiBadaneUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KohnegiBadaneUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KohnegiBadaneService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new KohnegiBadane(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.kohnegiBadane = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new KohnegiBadane();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.kohnegiBadane = entity;
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
