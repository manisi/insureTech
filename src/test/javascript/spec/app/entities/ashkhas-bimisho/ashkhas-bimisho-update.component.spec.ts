/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AshkhasBimishoUpdateComponent } from 'app/entities/ashkhas-bimisho/ashkhas-bimisho-update.component';
import { AshkhasBimishoService } from 'app/entities/ashkhas-bimisho/ashkhas-bimisho.service';
import { AshkhasBimisho } from 'app/shared/model/ashkhas-bimisho.model';

describe('Component Tests', () => {
    describe('AshkhasBimisho Management Update Component', () => {
        let comp: AshkhasBimishoUpdateComponent;
        let fixture: ComponentFixture<AshkhasBimishoUpdateComponent>;
        let service: AshkhasBimishoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AshkhasBimishoUpdateComponent]
            })
                .overrideTemplate(AshkhasBimishoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AshkhasBimishoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AshkhasBimishoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AshkhasBimisho(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ashkhas = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AshkhasBimisho();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ashkhas = entity;
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
