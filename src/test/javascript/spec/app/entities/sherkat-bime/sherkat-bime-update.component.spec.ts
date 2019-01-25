/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SherkatBimeUpdateComponent } from 'app/entities/sherkat-bime/sherkat-bime-update.component';
import { SherkatBimeService } from 'app/entities/sherkat-bime/sherkat-bime.service';
import { SherkatBime } from 'app/shared/model/sherkat-bime.model';

describe('Component Tests', () => {
    describe('SherkatBime Management Update Component', () => {
        let comp: SherkatBimeUpdateComponent;
        let fixture: ComponentFixture<SherkatBimeUpdateComponent>;
        let service: SherkatBimeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SherkatBimeUpdateComponent]
            })
                .overrideTemplate(SherkatBimeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SherkatBimeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SherkatBimeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SherkatBime(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sherkatBime = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SherkatBime();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sherkatBime = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
