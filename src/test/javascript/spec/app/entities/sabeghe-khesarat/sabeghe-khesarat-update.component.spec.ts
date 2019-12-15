/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SabegheKhesaratUpdateComponent } from 'app/entities/sabeghe-khesarat/sabeghe-khesarat-update.component';
import { SabegheKhesaratService } from 'app/entities/sabeghe-khesarat/sabeghe-khesarat.service';
import { SabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';

describe('Component Tests', () => {
    describe('SabegheKhesarat Management Update Component', () => {
        let comp: SabegheKhesaratUpdateComponent;
        let fixture: ComponentFixture<SabegheKhesaratUpdateComponent>;
        let service: SabegheKhesaratService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SabegheKhesaratUpdateComponent]
            })
                .overrideTemplate(SabegheKhesaratUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SabegheKhesaratUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SabegheKhesaratService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SabegheKhesarat(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sabegheKhesarat = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SabegheKhesarat();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sabegheKhesarat = entity;
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
