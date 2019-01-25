/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { JarimeDirkardUpdateComponent } from 'app/entities/jarime-dirkard/jarime-dirkard-update.component';
import { JarimeDirkardService } from 'app/entities/jarime-dirkard/jarime-dirkard.service';
import { JarimeDirkard } from 'app/shared/model/jarime-dirkard.model';

describe('Component Tests', () => {
    describe('JarimeDirkard Management Update Component', () => {
        let comp: JarimeDirkardUpdateComponent;
        let fixture: ComponentFixture<JarimeDirkardUpdateComponent>;
        let service: JarimeDirkardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [JarimeDirkardUpdateComponent]
            })
                .overrideTemplate(JarimeDirkardUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JarimeDirkardUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JarimeDirkardService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new JarimeDirkard(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jarimeDirkard = entity;
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
                    const entity = new JarimeDirkard();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jarimeDirkard = entity;
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
