/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { NerkhUpdateComponent } from 'app/entities/nerkh/nerkh-update.component';
import { NerkhService } from 'app/entities/nerkh/nerkh.service';
import { Nerkh } from 'app/shared/model/nerkh.model';

describe('Component Tests', () => {
    describe('Nerkh Management Update Component', () => {
        let comp: NerkhUpdateComponent;
        let fixture: ComponentFixture<NerkhUpdateComponent>;
        let service: NerkhService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [NerkhUpdateComponent]
            })
                .overrideTemplate(NerkhUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NerkhUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NerkhService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Nerkh(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nerkh = entity;
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
                    const entity = new Nerkh();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nerkh = entity;
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
