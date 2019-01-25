/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SabegheUpdateComponent } from 'app/entities/sabeghe/sabeghe-update.component';
import { SabegheService } from 'app/entities/sabeghe/sabeghe.service';
import { Sabeghe } from 'app/shared/model/sabeghe.model';

describe('Component Tests', () => {
    describe('Sabeghe Management Update Component', () => {
        let comp: SabegheUpdateComponent;
        let fixture: ComponentFixture<SabegheUpdateComponent>;
        let service: SabegheService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SabegheUpdateComponent]
            })
                .overrideTemplate(SabegheUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SabegheUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SabegheService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Sabeghe(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sabeghe = entity;
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
                    const entity = new Sabeghe();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sabeghe = entity;
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
