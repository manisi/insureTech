/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { JarimeDirkardDeleteDialogComponent } from 'app/entities/jarime-dirkard/jarime-dirkard-delete-dialog.component';
import { JarimeDirkardService } from 'app/entities/jarime-dirkard/jarime-dirkard.service';

describe('Component Tests', () => {
    describe('JarimeDirkard Management Delete Component', () => {
        let comp: JarimeDirkardDeleteDialogComponent;
        let fixture: ComponentFixture<JarimeDirkardDeleteDialogComponent>;
        let service: JarimeDirkardService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [JarimeDirkardDeleteDialogComponent]
            })
                .overrideTemplate(JarimeDirkardDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(JarimeDirkardDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JarimeDirkardService);
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
