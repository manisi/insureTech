import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';
import { SalesSarneshinCalcService } from './sales-sarneshin-calc.service';

@Component({
    selector: 'jhi-sales-sarneshin-calc-delete-dialog',
    templateUrl: './sales-sarneshin-calc-delete-dialog.component.html'
})
export class SalesSarneshinCalcDeleteDialogComponent {
    salesSarneshinCalc: ISalesSarneshinCalc;

    constructor(
        protected salesSarneshinCalcService: SalesSarneshinCalcService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.salesSarneshinCalcService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'salesSarneshinCalcListModification',
                content: 'Deleted an salesSarneshinCalc'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sales-sarneshin-calc-delete-popup',
    template: ''
})
export class SalesSarneshinCalcDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ salesSarneshinCalc }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SalesSarneshinCalcDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.salesSarneshinCalc = salesSarneshinCalc;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/sales-sarneshin-calc', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/sales-sarneshin-calc', { outlets: { popup: null } }]);
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
