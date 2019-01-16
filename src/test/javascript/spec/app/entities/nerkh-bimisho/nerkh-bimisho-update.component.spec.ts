/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { NerkhBimishoUpdateComponent } from 'app/entities/nerkh-bimisho/nerkh-bimisho-update.component';
import { NerkhBimishoService } from 'app/entities/nerkh-bimisho/nerkh-bimisho.service';
import { NerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';

describe('Component Tests', () => {
    describe('NerkhBimisho Management Update Component', () => {
        let comp: NerkhBimishoUpdateComponent;
        let fixture: ComponentFixture<NerkhBimishoUpdateComponent>;
        let service: NerkhBimishoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [NerkhBimishoUpdateComponent]
            })
                .overrideTemplate(NerkhBimishoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NerkhBimishoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NerkhBimishoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new NerkhBimisho(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.nerkh = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new NerkhBimisho();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.nerkh = entity;
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
