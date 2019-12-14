/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { VaziatBimeDeleteDialogComponent } from 'app/entities/vaziat-bime/vaziat-bime-delete-dialog.component';
import { VaziatBimeService } from 'app/entities/vaziat-bime/vaziat-bime.service';

describe('Component Tests', () => {
    describe('VaziatBime Management Delete Component', () => {
        let comp: VaziatBimeDeleteDialogComponent;
        let fixture: ComponentFixture<VaziatBimeDeleteDialogComponent>;
        let service: VaziatBimeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [VaziatBimeDeleteDialogComponent]
            })
                .overrideTemplate(VaziatBimeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VaziatBimeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VaziatBimeService);
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
