/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { AshkhasBimishoDeleteDialogComponent } from 'app/entities/ashkhas-bimisho/ashkhas-bimisho-delete-dialog.component';
import { AshkhasBimishoService } from 'app/entities/ashkhas-bimisho/ashkhas-bimisho.service';

describe('Component Tests', () => {
    describe('AshkhasBimisho Management Delete Component', () => {
        let comp: AshkhasBimishoDeleteDialogComponent;
        let fixture: ComponentFixture<AshkhasBimishoDeleteDialogComponent>;
        let service: AshkhasBimishoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AshkhasBimishoDeleteDialogComponent]
            })
                .overrideTemplate(AshkhasBimishoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AshkhasBimishoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AshkhasBimishoService);
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
