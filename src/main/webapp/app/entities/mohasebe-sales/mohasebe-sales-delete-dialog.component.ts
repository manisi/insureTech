import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMohasebeSales } from 'app/shared/model/mohasebe-sales.model';
import { MohasebeSalesService } from './mohasebe-sales.service';

@Component({
    selector: 'insutech-mohasebe-sales-delete-dialog',
    templateUrl: './mohasebe-sales-delete-dialog.component.html'
})
export class MohasebeSalesDeleteDialogComponent {
    mohasebeSales: IMohasebeSales;

    constructor(
        protected mohasebeSalesService: MohasebeSalesService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mohasebeSalesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mohasebeSalesListModification',
                content: 'Deleted an mohasebeSales'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-mohasebe-sales-delete-popup',
    template: ''
})
export class MohasebeSalesDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mohasebeSales }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MohasebeSalesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mohasebeSales = mohasebeSales;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/mohasebe-sales', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/mohasebe-sales', { outlets: { popup: null } }]);
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
