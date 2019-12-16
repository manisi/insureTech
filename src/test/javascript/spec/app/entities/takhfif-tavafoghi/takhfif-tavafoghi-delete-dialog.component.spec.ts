/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { TakhfifTavafoghiDeleteDialogComponent } from 'app/entities/takhfif-tavafoghi/takhfif-tavafoghi-delete-dialog.component';
import { TakhfifTavafoghiService } from 'app/entities/takhfif-tavafoghi/takhfif-tavafoghi.service';

describe('Component Tests', () => {
    describe('TakhfifTavafoghi Management Delete Component', () => {
        let comp: TakhfifTavafoghiDeleteDialogComponent;
        let fixture: ComponentFixture<TakhfifTavafoghiDeleteDialogComponent>;
        let service: TakhfifTavafoghiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [TakhfifTavafoghiDeleteDialogComponent]
            })
                .overrideTemplate(TakhfifTavafoghiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TakhfifTavafoghiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TakhfifTavafoghiService);
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
