/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { MohasebeSalesUpdateComponent } from 'app/entities/mohasebe-sales/mohasebe-sales-update.component';
import { MohasebeSalesService } from 'app/entities/mohasebe-sales/mohasebe-sales.service';
import { MohasebeSales } from 'app/shared/model/mohasebe-sales.model';

describe('Component Tests', () => {
    describe('MohasebeSales Management Update Component', () => {
        let comp: MohasebeSalesUpdateComponent;
        let fixture: ComponentFixture<MohasebeSalesUpdateComponent>;
        let service: MohasebeSalesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [MohasebeSalesUpdateComponent]
            })
                .overrideTemplate(MohasebeSalesUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MohasebeSalesUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MohasebeSalesService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MohasebeSales(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mohasebeSales = entity;
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
                    const entity = new MohasebeSales();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mohasebeSales = entity;
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
