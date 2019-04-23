import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMoredEstefadeSales } from 'app/shared/model/mored-estefade-sales.model';
import { MoredEstefadeSalesService } from './mored-estefade-sales.service';

@Component({
    selector: 'jhi-mored-estefade-sales-delete-dialog',
    templateUrl: './mored-estefade-sales-delete-dialog.component.html'
})
export class MoredEstefadeSalesDeleteDialogComponent {
    moredEstefadeSales: IMoredEstefadeSales;

    constructor(
        protected moredEstefadeSalesService: MoredEstefadeSalesService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.moredEstefadeSalesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'moredEstefadeSalesListModification',
                content: 'Deleted an moredEstefadeSales'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-mored-estefade-sales-delete-popup',
    template: ''
})
export class MoredEstefadeSalesDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ moredEstefadeSales }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MoredEstefadeSalesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.moredEstefadeSales = moredEstefadeSales;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/mored-estefade-sales', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/mored-estefade-sales', { outlets: { popup: null } }]);
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
