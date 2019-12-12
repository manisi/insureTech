/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { SaalSakhtDeleteDialogComponent } from 'app/entities/saal-sakht/saal-sakht-delete-dialog.component';
import { SaalSakhtService } from 'app/entities/saal-sakht/saal-sakht.service';

describe('Component Tests', () => {
    describe('SaalSakht Management Delete Component', () => {
        let comp: SaalSakhtDeleteDialogComponent;
        let fixture: ComponentFixture<SaalSakhtDeleteDialogComponent>;
        let service: SaalSakhtService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SaalSakhtDeleteDialogComponent]
            })
                .overrideTemplate(SaalSakhtDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SaalSakhtDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SaalSakhtService);
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
