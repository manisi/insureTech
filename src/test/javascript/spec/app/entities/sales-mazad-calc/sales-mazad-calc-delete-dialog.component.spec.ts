/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesMazadCalcDeleteDialogComponent } from 'app/entities/sales-mazad-calc/sales-mazad-calc-delete-dialog.component';
import { SalesMazadCalcService } from 'app/entities/sales-mazad-calc/sales-mazad-calc.service';

describe('Component Tests', () => {
    describe('SalesMazadCalc Management Delete Component', () => {
        let comp: SalesMazadCalcDeleteDialogComponent;
        let fixture: ComponentFixture<SalesMazadCalcDeleteDialogComponent>;
        let service: SalesMazadCalcService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesMazadCalcDeleteDialogComponent]
            })
                .overrideTemplate(SalesMazadCalcDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SalesMazadCalcDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SalesMazadCalcService);
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
