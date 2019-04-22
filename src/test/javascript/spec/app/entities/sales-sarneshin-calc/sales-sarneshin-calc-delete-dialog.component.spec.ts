/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesSarneshinCalcDeleteDialogComponent } from 'app/entities/sales-sarneshin-calc/sales-sarneshin-calc-delete-dialog.component';
import { SalesSarneshinCalcService } from 'app/entities/sales-sarneshin-calc/sales-sarneshin-calc.service';

describe('Component Tests', () => {
    describe('SalesSarneshinCalc Management Delete Component', () => {
        let comp: SalesSarneshinCalcDeleteDialogComponent;
        let fixture: ComponentFixture<SalesSarneshinCalcDeleteDialogComponent>;
        let service: SalesSarneshinCalcService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesSarneshinCalcDeleteDialogComponent]
            })
                .overrideTemplate(SalesSarneshinCalcDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SalesSarneshinCalcDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SalesSarneshinCalcService);
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
