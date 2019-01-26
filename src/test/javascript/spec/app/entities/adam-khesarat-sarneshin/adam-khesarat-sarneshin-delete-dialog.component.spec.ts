/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratSarneshinDeleteDialogComponent } from 'app/entities/adam-khesarat-sarneshin/adam-khesarat-sarneshin-delete-dialog.component';
import { AdamKhesaratSarneshinService } from 'app/entities/adam-khesarat-sarneshin/adam-khesarat-sarneshin.service';

describe('Component Tests', () => {
    describe('AdamKhesaratSarneshin Management Delete Component', () => {
        let comp: AdamKhesaratSarneshinDeleteDialogComponent;
        let fixture: ComponentFixture<AdamKhesaratSarneshinDeleteDialogComponent>;
        let service: AdamKhesaratSarneshinService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratSarneshinDeleteDialogComponent]
            })
                .overrideTemplate(AdamKhesaratSarneshinDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdamKhesaratSarneshinDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdamKhesaratSarneshinService);
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
