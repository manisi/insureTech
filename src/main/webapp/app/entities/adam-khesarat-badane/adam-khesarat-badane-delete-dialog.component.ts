import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';
import { AdamKhesaratBadaneService } from './adam-khesarat-badane.service';

@Component({
    selector: 'jhi-adam-khesarat-badane-delete-dialog',
    templateUrl: './adam-khesarat-badane-delete-dialog.component.html'
})
export class AdamKhesaratBadaneDeleteDialogComponent {
    adamKhesaratBadane: IAdamKhesaratBadane;

    constructor(
        protected adamKhesaratBadaneService: AdamKhesaratBadaneService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adamKhesaratBadaneService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'adamKhesaratBadaneListModification',
                content: 'Deleted an adamKhesaratBadane'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-adam-khesarat-badane-delete-popup',
    template: ''
})
export class AdamKhesaratBadaneDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adamKhesaratBadane }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdamKhesaratBadaneDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.adamKhesaratBadane = adamKhesaratBadane;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/adam-khesarat-badane', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/adam-khesarat-badane', { outlets: { popup: null } }]);
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
