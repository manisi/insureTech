import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';
import { NerkhBimishoService } from './nerkh-bimisho.service';

@Component({
    selector: 'insutech-nerkh-bimisho-delete-dialog',
    templateUrl: './nerkh-bimisho-delete-dialog.component.html'
})
export class NerkhBimishoDeleteDialogComponent {
    nerkh: INerkhBimisho;

    constructor(protected nerkhService: NerkhBimishoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

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
    selector: 'insutech-nerkh-bimisho-delete-popup',
    template: ''
})
export class NerkhBimishoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nerkh }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NerkhBimishoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.nerkh = nerkh;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
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
