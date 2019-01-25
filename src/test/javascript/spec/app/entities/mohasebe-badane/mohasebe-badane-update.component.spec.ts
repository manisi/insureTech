/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { MohasebeBadaneUpdateComponent } from 'app/entities/mohasebe-badane/mohasebe-badane-update.component';
import { MohasebeBadaneService } from 'app/entities/mohasebe-badane/mohasebe-badane.service';
import { MohasebeBadane } from 'app/shared/model/mohasebe-badane.model';

describe('Component Tests', () => {
    describe('MohasebeBadane Management Update Component', () => {
        let comp: MohasebeBadaneUpdateComponent;
        let fixture: ComponentFixture<MohasebeBadaneUpdateComponent>;
        let service: MohasebeBadaneService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [MohasebeBadaneUpdateComponent]
            })
                .overrideTemplate(MohasebeBadaneUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MohasebeBadaneUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MohasebeBadaneService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MohasebeBadane(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mohasebeBadane = entity;
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
                    const entity = new MohasebeBadane();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mohasebeBadane = entity;
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
