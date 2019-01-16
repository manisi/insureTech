/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { KhodroBimishoDeleteDialogComponent } from 'app/entities/khodro-bimisho/khodro-bimisho-delete-dialog.component';
import { KhodroBimishoService } from 'app/entities/khodro-bimisho/khodro-bimisho.service';

describe('Component Tests', () => {
    describe('KhodroBimisho Management Delete Component', () => {
        let comp: KhodroBimishoDeleteDialogComponent;
        let fixture: ComponentFixture<KhodroBimishoDeleteDialogComponent>;
        let service: KhodroBimishoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhodroBimishoDeleteDialogComponent]
            })
                .overrideTemplate(KhodroBimishoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhodroBimishoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhodroBimishoService);
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
