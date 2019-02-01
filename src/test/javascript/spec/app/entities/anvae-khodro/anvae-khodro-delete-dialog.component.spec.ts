/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { AnvaeKhodroDeleteDialogComponent } from 'app/entities/anvae-khodro/anvae-khodro-delete-dialog.component';
import { AnvaeKhodroService } from 'app/entities/anvae-khodro/anvae-khodro.service';

describe('Component Tests', () => {
    describe('AnvaeKhodro Management Delete Component', () => {
        let comp: AnvaeKhodroDeleteDialogComponent;
        let fixture: ComponentFixture<AnvaeKhodroDeleteDialogComponent>;
        let service: AnvaeKhodroService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AnvaeKhodroDeleteDialogComponent]
            })
                .overrideTemplate(AnvaeKhodroDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AnvaeKhodroDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AnvaeKhodroService);
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
