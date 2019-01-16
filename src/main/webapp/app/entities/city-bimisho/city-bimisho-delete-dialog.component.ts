import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICityBimisho } from 'app/shared/model/city-bimisho.model';
import { CityBimishoService } from './city-bimisho.service';

@Component({
    selector: 'insutech-city-bimisho-delete-dialog',
    templateUrl: './city-bimisho-delete-dialog.component.html'
})
export class CityBimishoDeleteDialogComponent {
    city: ICityBimisho;

    constructor(protected cityService: CityBimishoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cityListModification',
                content: 'Deleted an city'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-city-bimisho-delete-popup',
    template: ''
})
export class CityBimishoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ city }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CityBimishoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.city = city;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
