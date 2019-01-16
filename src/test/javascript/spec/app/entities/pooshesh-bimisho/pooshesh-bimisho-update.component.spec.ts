/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { PoosheshBimishoUpdateComponent } from 'app/entities/pooshesh-bimisho/pooshesh-bimisho-update.component';
import { PoosheshBimishoService } from 'app/entities/pooshesh-bimisho/pooshesh-bimisho.service';
import { PoosheshBimisho } from 'app/shared/model/pooshesh-bimisho.model';

describe('Component Tests', () => {
    describe('PoosheshBimisho Management Update Component', () => {
        let comp: PoosheshBimishoUpdateComponent;
        let fixture: ComponentFixture<PoosheshBimishoUpdateComponent>;
        let service: PoosheshBimishoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [PoosheshBimishoUpdateComponent]
            })
                .overrideTemplate(PoosheshBimishoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PoosheshBimishoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PoosheshBimishoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PoosheshBimisho(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pooshesh = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PoosheshBimisho();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pooshesh = entity;
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
