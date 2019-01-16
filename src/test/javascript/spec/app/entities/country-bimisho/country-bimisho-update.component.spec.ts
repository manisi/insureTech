/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { CountryBimishoUpdateComponent } from 'app/entities/country-bimisho/country-bimisho-update.component';
import { CountryBimishoService } from 'app/entities/country-bimisho/country-bimisho.service';
import { CountryBimisho } from 'app/shared/model/country-bimisho.model';

describe('Component Tests', () => {
    describe('CountryBimisho Management Update Component', () => {
        let comp: CountryBimishoUpdateComponent;
        let fixture: ComponentFixture<CountryBimishoUpdateComponent>;
        let service: CountryBimishoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [CountryBimishoUpdateComponent]
            })
                .overrideTemplate(CountryBimishoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CountryBimishoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountryBimishoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CountryBimisho(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.country = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CountryBimisho();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.country = entity;
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
