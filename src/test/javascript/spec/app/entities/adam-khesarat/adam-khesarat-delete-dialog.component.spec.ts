/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratDeleteDialogComponent } from 'app/entities/adam-khesarat/adam-khesarat-delete-dialog.component';
import { AdamKhesaratService } from 'app/entities/adam-khesarat/adam-khesarat.service';

describe('Component Tests', () => {
    describe('AdamKhesarat Management Delete Component', () => {
        let comp: AdamKhesaratDeleteDialogComponent;
        let fixture: ComponentFixture<AdamKhesaratDeleteDialogComponent>;
        let service: AdamKhesaratService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratDeleteDialogComponent]
            })
                .overrideTemplate(AdamKhesaratDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdamKhesaratDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdamKhesaratService);
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
