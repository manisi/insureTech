/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { GroupOperationsDataDeleteDialogComponent } from 'app/entities/group-operations-data/group-operations-data-delete-dialog.component';
import { GroupOperationsDataService } from 'app/entities/group-operations-data/group-operations-data.service';

describe('Component Tests', () => {
    describe('GroupOperationsData Management Delete Component', () => {
        let comp: GroupOperationsDataDeleteDialogComponent;
        let fixture: ComponentFixture<GroupOperationsDataDeleteDialogComponent>;
        let service: GroupOperationsDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [GroupOperationsDataDeleteDialogComponent]
            })
                .overrideTemplate(GroupOperationsDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroupOperationsDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GroupOperationsDataService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
