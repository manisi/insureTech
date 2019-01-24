/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSalesDeleteDialogComponent } from 'app/entities/khesarat-sales/khesarat-sales-delete-dialog.component';
import { KhesaratSalesService } from 'app/entities/khesarat-sales/khesarat-sales.service';

describe('Component Tests', () => {
    describe('KhesaratSales Management Delete Component', () => {
        let comp: KhesaratSalesDeleteDialogComponent;
        let fixture: ComponentFixture<KhesaratSalesDeleteDialogComponent>;
        let service: KhesaratSalesService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSalesDeleteDialogComponent]
            })
                .overrideTemplate(KhesaratSalesDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhesaratSalesDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhesaratSalesService);
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
