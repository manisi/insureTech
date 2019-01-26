import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKohnegiBadane } from 'app/shared/model/kohnegi-badane.model';
import { KohnegiBadaneService } from './kohnegi-badane.service';

@Component({
    selector: 'jhi-kohnegi-badane-delete-dialog',
    templateUrl: './kohnegi-badane-delete-dialog.component.html'
})
export class KohnegiBadaneDeleteDialogComponent {
    kohnegiBadane: IKohnegiBadane;

    constructor(
        protected kohnegiBadaneService: KohnegiBadaneService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.kohnegiBadaneService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'kohnegiBadaneListModification',
                content: 'Deleted an kohnegiBadane'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-kohnegi-badane-delete-popup',
    template: ''
})
export class KohnegiBadaneDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kohnegiBadane }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KohnegiBadaneDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.kohnegiBadane = kohnegiBadane;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/kohnegi-badane', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/kohnegi-badane', { outlets: { popup: null } }]);
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
