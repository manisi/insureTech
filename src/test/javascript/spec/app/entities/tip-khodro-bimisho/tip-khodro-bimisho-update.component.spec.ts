/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { TipKhodroBimishoUpdateComponent } from 'app/entities/tip-khodro-bimisho/tip-khodro-bimisho-update.component';
import { TipKhodroBimishoService } from 'app/entities/tip-khodro-bimisho/tip-khodro-bimisho.service';
import { TipKhodroBimisho } from 'app/shared/model/tip-khodro-bimisho.model';

describe('Component Tests', () => {
    describe('TipKhodroBimisho Management Update Component', () => {
        let comp: TipKhodroBimishoUpdateComponent;
        let fixture: ComponentFixture<TipKhodroBimishoUpdateComponent>;
        let service: TipKhodroBimishoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [TipKhodroBimishoUpdateComponent]
            })
                .overrideTemplate(TipKhodroBimishoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipKhodroBimishoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipKhodroBimishoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TipKhodroBimisho(123);
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
                const entity = new TipKhodroBimisho();
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
