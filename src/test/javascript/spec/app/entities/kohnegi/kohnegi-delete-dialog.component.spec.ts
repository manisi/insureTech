/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { KohnegiDeleteDialogComponent } from 'app/entities/kohnegi/kohnegi-delete-dialog.component';
import { KohnegiService } from 'app/entities/kohnegi/kohnegi.service';

describe('Component Tests', () => {
    describe('Kohnegi Management Delete Component', () => {
        let comp: KohnegiDeleteDialogComponent;
        let fixture: ComponentFixture<KohnegiDeleteDialogComponent>;
        let service: KohnegiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KohnegiDeleteDialogComponent]
            })
                .overrideTemplate(KohnegiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KohnegiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KohnegiService);
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
