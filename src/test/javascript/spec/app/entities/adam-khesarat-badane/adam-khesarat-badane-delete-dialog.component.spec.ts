/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratBadaneDeleteDialogComponent } from 'app/entities/adam-khesarat-badane/adam-khesarat-badane-delete-dialog.component';
import { AdamKhesaratBadaneService } from 'app/entities/adam-khesarat-badane/adam-khesarat-badane.service';

describe('Component Tests', () => {
    describe('AdamKhesaratBadane Management Delete Component', () => {
        let comp: AdamKhesaratBadaneDeleteDialogComponent;
        let fixture: ComponentFixture<AdamKhesaratBadaneDeleteDialogComponent>;
        let service: AdamKhesaratBadaneService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratBadaneDeleteDialogComponent]
            })
                .overrideTemplate(AdamKhesaratBadaneDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdamKhesaratBadaneDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdamKhesaratBadaneService);
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
