/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { GrouhKhodroDeleteDialogComponent } from 'app/entities/grouh-khodro/grouh-khodro-delete-dialog.component';
import { GrouhKhodroService } from 'app/entities/grouh-khodro/grouh-khodro.service';

describe('Component Tests', () => {
    describe('GrouhKhodro Management Delete Component', () => {
        let comp: GrouhKhodroDeleteDialogComponent;
        let fixture: ComponentFixture<GrouhKhodroDeleteDialogComponent>;
        let service: GrouhKhodroService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [GrouhKhodroDeleteDialogComponent]
            })
                .overrideTemplate(GrouhKhodroDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GrouhKhodroDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GrouhKhodroService);
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
