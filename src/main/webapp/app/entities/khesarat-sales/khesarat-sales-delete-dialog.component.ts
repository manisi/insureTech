import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKhesaratSales } from 'app/shared/model/khesarat-sales.model';
import { KhesaratSalesService } from './khesarat-sales.service';

@Component({
    selector: 'jhi-khesarat-sales-delete-dialog',
    templateUrl: './khesarat-sales-delete-dialog.component.html'
})
export class KhesaratSalesDeleteDialogComponent {
    khesaratSales: IKhesaratSales;

    constructor(
        protected khesaratSalesService: KhesaratSalesService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.khesaratSalesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'khesaratSalesListModification',
                content: 'Deleted an khesaratSales'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-khesarat-sales-delete-popup',
    template: ''
})
export class KhesaratSalesDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khesaratSales }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KhesaratSalesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.khesaratSales = khesaratSales;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/khesarat-sales', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/khesarat-sales', { outlets: { popup: null } }]);
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
