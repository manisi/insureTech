import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INerkh } from 'app/shared/model/nerkh.model';
import { NerkhService } from './nerkh.service';

@Component({
    selector: 'insutech-nerkh-delete-dialog',
    templateUrl: './nerkh-delete-dialog.component.html'
})
export class NerkhDeleteDialogComponent {
    nerkh: INerkh;

    constructor(protected nerkhService: NerkhService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nerkhService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nerkhListModification',
                content: 'Deleted an nerkh'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-nerkh-delete-popup',
    template: ''
})
export class NerkhDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nerkh }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NerkhDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.nerkh = nerkh;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/nerkh', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/nerkh', { outlets: { popup: null } }]);
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
