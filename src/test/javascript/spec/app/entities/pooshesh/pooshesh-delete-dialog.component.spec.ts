/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { PoosheshDeleteDialogComponent } from 'app/entities/pooshesh/pooshesh-delete-dialog.component';
import { PoosheshService } from 'app/entities/pooshesh/pooshesh.service';

describe('Component Tests', () => {
    describe('Pooshesh Management Delete Component', () => {
        let comp: PoosheshDeleteDialogComponent;
        let fixture: ComponentFixture<PoosheshDeleteDialogComponent>;
        let service: PoosheshService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [PoosheshDeleteDialogComponent]
            })
                .overrideTemplate(PoosheshDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PoosheshDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PoosheshService);
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
