/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { SherkatBimeDeleteDialogComponent } from 'app/entities/sherkat-bime/sherkat-bime-delete-dialog.component';
import { SherkatBimeService } from 'app/entities/sherkat-bime/sherkat-bime.service';

describe('Component Tests', () => {
    describe('SherkatBime Management Delete Component', () => {
        let comp: SherkatBimeDeleteDialogComponent;
        let fixture: ComponentFixture<SherkatBimeDeleteDialogComponent>;
        let service: SherkatBimeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SherkatBimeDeleteDialogComponent]
            })
                .overrideTemplate(SherkatBimeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SherkatBimeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SherkatBimeService);
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
