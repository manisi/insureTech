/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { EstelaamSalesNerkhUpdateComponent } from 'app/entities/estelaam-sales-nerkh/estelaam-sales-nerkh-update.component';
import { EstelaamSalesNerkhService } from 'app/entities/estelaam-sales-nerkh/estelaam-sales-nerkh.service';
import { EstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';

describe('Component Tests', () => {
    describe('EstelaamSalesNerkh Management Update Component', () => {
        let comp: EstelaamSalesNerkhUpdateComponent;
        let fixture: ComponentFixture<EstelaamSalesNerkhUpdateComponent>;
        let service: EstelaamSalesNerkhService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [EstelaamSalesNerkhUpdateComponent]
            })
                .overrideTemplate(EstelaamSalesNerkhUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EstelaamSalesNerkhUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EstelaamSalesNerkhService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new EstelaamSalesNerkh(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.estelaamSalesNerkh = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new EstelaamSalesNerkh();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.estelaamSalesNerkh = entity;
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
