import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISalesMazadCalc } from 'app/shared/model/sales-mazad-calc.model';
import { SalesMazadCalcService } from './sales-mazad-calc.service';

@Component({
    selector: 'jhi-sales-mazad-calc-delete-dialog',
    templateUrl: './sales-mazad-calc-delete-dialog.component.html'
})
export class SalesMazadCalcDeleteDialogComponent {
    salesMazadCalc: ISalesMazadCalc;

    constructor(
        protected salesMazadCalcService: SalesMazadCalcService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.salesMazadCalcService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'salesMazadCalcListModification',
                content: 'Deleted an salesMazadCalc'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sales-mazad-calc-delete-popup',
    template: ''
})
export class SalesMazadCalcDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ salesMazadCalc }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SalesMazadCalcDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.salesMazadCalc = salesMazadCalc;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/sales-mazad-calc', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/sales-mazad-calc', { outlets: { popup: null } }]);
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
