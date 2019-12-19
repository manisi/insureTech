/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSalesMaliDeleteDialogComponent } from 'app/entities/khesarat-sales-mali/khesarat-sales-mali-delete-dialog.component';
import { KhesaratSalesMaliService } from 'app/entities/khesarat-sales-mali/khesarat-sales-mali.service';

describe('Component Tests', () => {
    describe('KhesaratSalesMali Management Delete Component', () => {
        let comp: KhesaratSalesMaliDeleteDialogComponent;
        let fixture: ComponentFixture<KhesaratSalesMaliDeleteDialogComponent>;
        let service: KhesaratSalesMaliService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSalesMaliDeleteDialogComponent]
            })
                .overrideTemplate(KhesaratSalesMaliDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhesaratSalesMaliDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhesaratSalesMaliService);
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
