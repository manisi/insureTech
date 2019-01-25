/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhodroUpdateComponent } from 'app/entities/khodro/khodro-update.component';
import { KhodroService } from 'app/entities/khodro/khodro.service';
import { Khodro } from 'app/shared/model/khodro.model';

describe('Component Tests', () => {
    describe('Khodro Management Update Component', () => {
        let comp: KhodroUpdateComponent;
        let fixture: ComponentFixture<KhodroUpdateComponent>;
        let service: KhodroService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhodroUpdateComponent]
            })
                .overrideTemplate(KhodroUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(KhodroUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhodroService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Khodro(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.khodro = entity;
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
                    const entity = new Khodro();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.khodro = entity;
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
