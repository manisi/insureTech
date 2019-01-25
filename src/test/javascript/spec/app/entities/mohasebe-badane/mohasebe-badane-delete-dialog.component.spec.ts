/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { MohasebeBadaneDeleteDialogComponent } from 'app/entities/mohasebe-badane/mohasebe-badane-delete-dialog.component';
import { MohasebeBadaneService } from 'app/entities/mohasebe-badane/mohasebe-badane.service';

describe('Component Tests', () => {
    describe('MohasebeBadane Management Delete Component', () => {
        let comp: MohasebeBadaneDeleteDialogComponent;
        let fixture: ComponentFixture<MohasebeBadaneDeleteDialogComponent>;
        let service: MohasebeBadaneService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [MohasebeBadaneDeleteDialogComponent]
            })
                .overrideTemplate(MohasebeBadaneDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MohasebeBadaneDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MohasebeBadaneService);
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
