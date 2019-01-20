/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { TipKhodroUpdateComponent } from 'app/entities/tip-khodro/tip-khodro-update.component';
import { TipKhodroService } from 'app/entities/tip-khodro/tip-khodro.service';
import { TipKhodro } from 'app/shared/model/tip-khodro.model';

describe('Component Tests', () => {
    describe('TipKhodro Management Update Component', () => {
        let comp: TipKhodroUpdateComponent;
        let fixture: ComponentFixture<TipKhodroUpdateComponent>;
        let service: TipKhodroService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [TipKhodroUpdateComponent]
            })
                .overrideTemplate(TipKhodroUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipKhodroUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipKhodroService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipKhodro(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipKhodro = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipKhodro();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.tipKhodro = entity;
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
