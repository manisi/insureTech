/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsurancestartTestModule } from '../../../test.module';
import { CityBimishoDeleteDialogComponent } from 'app/entities/city-bimisho/city-bimisho-delete-dialog.component';
import { CityBimishoService } from 'app/entities/city-bimisho/city-bimisho.service';

describe('Component Tests', () => {
    describe('CityBimisho Management Delete Component', () => {
        let comp: CityBimishoDeleteDialogComponent;
        let fixture: ComponentFixture<CityBimishoDeleteDialogComponent>;
        let service: CityBimishoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [CityBimishoDeleteDialogComponent]
            })
                .overrideTemplate(CityBimishoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CityBimishoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CityBimishoService);
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
