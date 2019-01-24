/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { SabegheDeleteDialogComponent } from 'app/entities/sabeghe/sabeghe-delete-dialog.component';
import { SabegheService } from 'app/entities/sabeghe/sabeghe.service';

describe('Component Tests', () => {
    describe('Sabeghe Management Delete Component', () => {
        let comp: SabegheDeleteDialogComponent;
        let fixture: ComponentFixture<SabegheDeleteDialogComponent>;
        let service: SabegheService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SabegheDeleteDialogComponent]
            })
                .overrideTemplate(SabegheDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SabegheDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SabegheService);
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
