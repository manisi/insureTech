/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { TakhfifTavafoghiUpdateComponent } from 'app/entities/takhfif-tavafoghi/takhfif-tavafoghi-update.component';
import { TakhfifTavafoghiService } from 'app/entities/takhfif-tavafoghi/takhfif-tavafoghi.service';
import { TakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';

describe('Component Tests', () => {
    describe('TakhfifTavafoghi Management Update Component', () => {
        let comp: TakhfifTavafoghiUpdateComponent;
        let fixture: ComponentFixture<TakhfifTavafoghiUpdateComponent>;
        let service: TakhfifTavafoghiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [TakhfifTavafoghiUpdateComponent]
            })
                .overrideTemplate(TakhfifTavafoghiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TakhfifTavafoghiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TakhfifTavafoghiService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TakhfifTavafoghi(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.takhfifTavafoghi = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TakhfifTavafoghi();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.takhfifTavafoghi = entity;
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
