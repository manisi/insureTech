/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { KohnegiBadaneDeleteDialogComponent } from 'app/entities/kohnegi-badane/kohnegi-badane-delete-dialog.component';
import { KohnegiBadaneService } from 'app/entities/kohnegi-badane/kohnegi-badane.service';

describe('Component Tests', () => {
    describe('KohnegiBadane Management Delete Component', () => {
        let comp: KohnegiBadaneDeleteDialogComponent;
        let fixture: ComponentFixture<KohnegiBadaneDeleteDialogComponent>;
        let service: KohnegiBadaneService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KohnegiBadaneDeleteDialogComponent]
            })
                .overrideTemplate(KohnegiBadaneDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KohnegiBadaneDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KohnegiBadaneService);
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
