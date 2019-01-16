/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { PoosheshBimishoDeleteDialogComponent } from 'app/entities/pooshesh-bimisho/pooshesh-bimisho-delete-dialog.component';
import { PoosheshBimishoService } from 'app/entities/pooshesh-bimisho/pooshesh-bimisho.service';

describe('Component Tests', () => {
    describe('PoosheshBimisho Management Delete Component', () => {
        let comp: PoosheshBimishoDeleteDialogComponent;
        let fixture: ComponentFixture<PoosheshBimishoDeleteDialogComponent>;
        let service: PoosheshBimishoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [PoosheshBimishoDeleteDialogComponent]
            })
                .overrideTemplate(PoosheshBimishoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PoosheshBimishoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PoosheshBimishoService);
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
