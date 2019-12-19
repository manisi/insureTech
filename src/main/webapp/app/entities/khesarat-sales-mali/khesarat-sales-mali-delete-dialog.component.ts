import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';
import { KhesaratSalesMaliService } from './khesarat-sales-mali.service';

@Component({
    selector: 'jhi-khesarat-sales-mali-delete-dialog',
    templateUrl: './khesarat-sales-mali-delete-dialog.component.html'
})
export class KhesaratSalesMaliDeleteDialogComponent {
    khesaratSalesMali: IKhesaratSalesMali;

    constructor(
        protected khesaratSalesMaliService: KhesaratSalesMaliService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.khesaratSalesMaliService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'khesaratSalesMaliListModification',
                content: 'Deleted an khesaratSalesMali'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-khesarat-sales-mali-delete-popup',
    template: ''
})
export class KhesaratSalesMaliDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khesaratSalesMali }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KhesaratSalesMaliDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.khesaratSalesMali = khesaratSalesMali;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/khesarat-sales-mali', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/khesarat-sales-mali', { outlets: { popup: null } }]);
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
