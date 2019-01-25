/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { NoeSabegheDeleteDialogComponent } from 'app/entities/noe-sabeghe/noe-sabeghe-delete-dialog.component';
import { NoeSabegheService } from 'app/entities/noe-sabeghe/noe-sabeghe.service';

describe('Component Tests', () => {
    describe('NoeSabeghe Management Delete Component', () => {
        let comp: NoeSabegheDeleteDialogComponent;
        let fixture: ComponentFixture<NoeSabegheDeleteDialogComponent>;
        let service: NoeSabegheService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [NoeSabegheDeleteDialogComponent]
            })
                .overrideTemplate(NoeSabegheDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NoeSabegheDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NoeSabegheService);
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
