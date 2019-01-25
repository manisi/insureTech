/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { TipKhodroDeleteDialogComponent } from 'app/entities/tip-khodro/tip-khodro-delete-dialog.component';
import { TipKhodroService } from 'app/entities/tip-khodro/tip-khodro.service';

describe('Component Tests', () => {
    describe('TipKhodro Management Delete Component', () => {
        let comp: TipKhodroDeleteDialogComponent;
        let fixture: ComponentFixture<TipKhodroDeleteDialogComponent>;
        let service: TipKhodroService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [TipKhodroDeleteDialogComponent]
            })
                .overrideTemplate(TipKhodroDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipKhodroDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipKhodroService);
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
