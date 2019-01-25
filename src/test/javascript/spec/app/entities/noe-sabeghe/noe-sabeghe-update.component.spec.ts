/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { NoeSabegheUpdateComponent } from 'app/entities/noe-sabeghe/noe-sabeghe-update.component';
import { NoeSabegheService } from 'app/entities/noe-sabeghe/noe-sabeghe.service';
import { NoeSabeghe } from 'app/shared/model/noe-sabeghe.model';

describe('Component Tests', () => {
    describe('NoeSabeghe Management Update Component', () => {
        let comp: NoeSabegheUpdateComponent;
        let fixture: ComponentFixture<NoeSabegheUpdateComponent>;
        let service: NoeSabegheService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [NoeSabegheUpdateComponent]
            })
                .overrideTemplate(NoeSabegheUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NoeSabegheUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NoeSabegheService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NoeSabeghe(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.noeSabeghe = entity;
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
                    const entity = new NoeSabeghe();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.noeSabeghe = entity;
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
