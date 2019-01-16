/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { CityBimishoUpdateComponent } from 'app/entities/city-bimisho/city-bimisho-update.component';
import { CityBimishoService } from 'app/entities/city-bimisho/city-bimisho.service';
import { CityBimisho } from 'app/shared/model/city-bimisho.model';

describe('Component Tests', () => {
    describe('CityBimisho Management Update Component', () => {
        let comp: CityBimishoUpdateComponent;
        let fixture: ComponentFixture<CityBimishoUpdateComponent>;
        let service: CityBimishoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [CityBimishoUpdateComponent]
            })
                .overrideTemplate(CityBimishoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CityBimishoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CityBimishoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CityBimisho(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.city = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CityBimisho();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.city = entity;
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
