import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMohasebeBadane } from 'app/shared/model/mohasebe-badane.model';
import { MohasebeBadaneService } from './mohasebe-badane.service';

@Component({
    selector: 'insutech-mohasebe-badane-delete-dialog',
    templateUrl: './mohasebe-badane-delete-dialog.component.html'
})
export class MohasebeBadaneDeleteDialogComponent {
    mohasebeBadane: IMohasebeBadane;

    constructor(
        protected mohasebeBadaneService: MohasebeBadaneService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mohasebeBadaneService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mohasebeBadaneListModification',
                content: 'Deleted an mohasebeBadane'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-mohasebe-badane-delete-popup',
    template: ''
})
export class MohasebeBadaneDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mohasebeBadane }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MohasebeBadaneDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mohasebeBadane = mohasebeBadane;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/mohasebe-badane', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/mohasebe-badane', { outlets: { popup: null } }]);
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
