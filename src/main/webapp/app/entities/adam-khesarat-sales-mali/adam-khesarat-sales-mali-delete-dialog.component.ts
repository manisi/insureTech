import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';
import { AdamKhesaratSalesMaliService } from './adam-khesarat-sales-mali.service';

@Component({
    selector: 'jhi-adam-khesarat-sales-mali-delete-dialog',
    templateUrl: './adam-khesarat-sales-mali-delete-dialog.component.html'
})
export class AdamKhesaratSalesMaliDeleteDialogComponent {
    adamKhesaratSalesMali: IAdamKhesaratSalesMali;

    constructor(
        protected adamKhesaratSalesMaliService: AdamKhesaratSalesMaliService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adamKhesaratSalesMaliService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'adamKhesaratSalesMaliListModification',
                content: 'Deleted an adamKhesaratSalesMali'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-adam-khesarat-sales-mali-delete-popup',
    template: ''
})
export class AdamKhesaratSalesMaliDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adamKhesaratSalesMali }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdamKhesaratSalesMaliDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.adamKhesaratSalesMali = adamKhesaratSalesMali;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/adam-khesarat-sales-mali', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/adam-khesarat-sales-mali', { outlets: { popup: null } }]);
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
