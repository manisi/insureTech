/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SherkatBimeBimishoUpdateComponent } from 'app/entities/sherkat-bime-bimisho/sherkat-bime-bimisho-update.component';
import { SherkatBimeBimishoService } from 'app/entities/sherkat-bime-bimisho/sherkat-bime-bimisho.service';
import { SherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';

describe('Component Tests', () => {
    describe('SherkatBimeBimisho Management Update Component', () => {
        let comp: SherkatBimeBimishoUpdateComponent;
        let fixture: ComponentFixture<SherkatBimeBimishoUpdateComponent>;
        let service: SherkatBimeBimishoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SherkatBimeBimishoUpdateComponent]
            })
                .overrideTemplate(SherkatBimeBimishoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SherkatBimeBimishoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SherkatBimeBimishoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SherkatBimeBimisho(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sherkatBime = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SherkatBimeBimisho();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sherkatBime = entity;
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
