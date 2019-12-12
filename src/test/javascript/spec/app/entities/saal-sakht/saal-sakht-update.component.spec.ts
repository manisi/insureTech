/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SaalSakhtUpdateComponent } from 'app/entities/saal-sakht/saal-sakht-update.component';
import { SaalSakhtService } from 'app/entities/saal-sakht/saal-sakht.service';
import { SaalSakht } from 'app/shared/model/saal-sakht.model';

describe('Component Tests', () => {
    describe('SaalSakht Management Update Component', () => {
        let comp: SaalSakhtUpdateComponent;
        let fixture: ComponentFixture<SaalSakhtUpdateComponent>;
        let service: SaalSakhtService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SaalSakhtUpdateComponent]
            })
                .overrideTemplate(SaalSakhtUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SaalSakhtUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SaalSakhtService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SaalSakht(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.saalSakht = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SaalSakht();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.saalSakht = entity;
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
