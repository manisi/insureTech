import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISalesJaniCalc } from 'app/shared/model/sales-jani-calc.model';
import { SalesJaniCalcService } from './sales-jani-calc.service';

@Component({
    selector: 'jhi-sales-jani-calc-delete-dialog',
    templateUrl: './sales-jani-calc-delete-dialog.component.html'
})
export class SalesJaniCalcDeleteDialogComponent {
    salesJaniCalc: ISalesJaniCalc;

    constructor(
        protected salesJaniCalcService: SalesJaniCalcService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.salesJaniCalcService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'salesJaniCalcListModification',
                content: 'Deleted an salesJaniCalc'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sales-jani-calc-delete-popup',
    template: ''
})
export class SalesJaniCalcDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ salesJaniCalc }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SalesJaniCalcDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.salesJaniCalc = salesJaniCalc;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/sales-jani-calc', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/sales-jani-calc', { outlets: { popup: null } }]);
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
