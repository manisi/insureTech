/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { MoredEstefadeSalesDeleteDialogComponent } from 'app/entities/mored-estefade-sales/mored-estefade-sales-delete-dialog.component';
import { MoredEstefadeSalesService } from 'app/entities/mored-estefade-sales/mored-estefade-sales.service';

describe('Component Tests', () => {
    describe('MoredEstefadeSales Management Delete Component', () => {
        let comp: MoredEstefadeSalesDeleteDialogComponent;
        let fixture: ComponentFixture<MoredEstefadeSalesDeleteDialogComponent>;
        let service: MoredEstefadeSalesService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [MoredEstefadeSalesDeleteDialogComponent]
            })
                .overrideTemplate(MoredEstefadeSalesDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MoredEstefadeSalesDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MoredEstefadeSalesService);
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
