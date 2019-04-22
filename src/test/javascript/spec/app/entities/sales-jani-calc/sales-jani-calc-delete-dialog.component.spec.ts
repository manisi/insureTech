/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { SalesJaniCalcDeleteDialogComponent } from 'app/entities/sales-jani-calc/sales-jani-calc-delete-dialog.component';
import { SalesJaniCalcService } from 'app/entities/sales-jani-calc/sales-jani-calc.service';

describe('Component Tests', () => {
    describe('SalesJaniCalc Management Delete Component', () => {
        let comp: SalesJaniCalcDeleteDialogComponent;
        let fixture: ComponentFixture<SalesJaniCalcDeleteDialogComponent>;
        let service: SalesJaniCalcService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SalesJaniCalcDeleteDialogComponent]
            })
                .overrideTemplate(SalesJaniCalcDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SalesJaniCalcDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SalesJaniCalcService);
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
