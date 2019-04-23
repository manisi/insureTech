/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { MoredEstefadeSalesUpdateComponent } from 'app/entities/mored-estefade-sales/mored-estefade-sales-update.component';
import { MoredEstefadeSalesService } from 'app/entities/mored-estefade-sales/mored-estefade-sales.service';
import { MoredEstefadeSales } from 'app/shared/model/mored-estefade-sales.model';

describe('Component Tests', () => {
    describe('MoredEstefadeSales Management Update Component', () => {
        let comp: MoredEstefadeSalesUpdateComponent;
        let fixture: ComponentFixture<MoredEstefadeSalesUpdateComponent>;
        let service: MoredEstefadeSalesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [MoredEstefadeSalesUpdateComponent]
            })
                .overrideTemplate(MoredEstefadeSalesUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MoredEstefadeSalesUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MoredEstefadeSalesService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new MoredEstefadeSales(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.moredEstefadeSales = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new MoredEstefadeSales();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.moredEstefadeSales = entity;
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
