import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdamKhesarat } from 'app/shared/model/adam-khesarat.model';
import { AdamKhesaratService } from './adam-khesarat.service';

@Component({
    selector: 'jhi-adam-khesarat-delete-dialog',
    templateUrl: './adam-khesarat-delete-dialog.component.html'
})
export class AdamKhesaratDeleteDialogComponent {
    adamKhesarat: IAdamKhesarat;

    constructor(
        protected adamKhesaratService: AdamKhesaratService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adamKhesaratService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'adamKhesaratListModification',
                content: 'Deleted an adamKhesarat'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-adam-khesarat-delete-popup',
    template: ''
})
export class AdamKhesaratDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adamKhesarat }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdamKhesaratDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.adamKhesarat = adamKhesarat;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/adam-khesarat', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/adam-khesarat', { outlets: { popup: null } }]);
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
