/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSrneshinDeleteDialogComponent } from 'app/entities/khesarat-srneshin/khesarat-srneshin-delete-dialog.component';
import { KhesaratSrneshinService } from 'app/entities/khesarat-srneshin/khesarat-srneshin.service';

describe('Component Tests', () => {
    describe('KhesaratSrneshin Management Delete Component', () => {
        let comp: KhesaratSrneshinDeleteDialogComponent;
        let fixture: ComponentFixture<KhesaratSrneshinDeleteDialogComponent>;
        let service: KhesaratSrneshinService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSrneshinDeleteDialogComponent]
            })
                .overrideTemplate(KhesaratSrneshinDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhesaratSrneshinDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhesaratSrneshinService);
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
