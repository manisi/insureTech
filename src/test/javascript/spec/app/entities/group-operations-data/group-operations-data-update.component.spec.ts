/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { GroupOperationsDataUpdateComponent } from 'app/entities/group-operations-data/group-operations-data-update.component';
import { GroupOperationsDataService } from 'app/entities/group-operations-data/group-operations-data.service';
import { GroupOperationsData } from 'app/shared/model/group-operations-data.model';

describe('Component Tests', () => {
    describe('GroupOperationsData Management Update Component', () => {
        let comp: GroupOperationsDataUpdateComponent;
        let fixture: ComponentFixture<GroupOperationsDataUpdateComponent>;
        let service: GroupOperationsDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [GroupOperationsDataUpdateComponent]
            })
                .overrideTemplate(GroupOperationsDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GroupOperationsDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroupOperationsDataService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new GroupOperationsData(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.groupOperationsData = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new GroupOperationsData();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.groupOperationsData = entity;
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
