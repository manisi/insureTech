import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKhodroBimisho } from 'app/shared/model/khodro-bimisho.model';
import { KhodroBimishoService } from './khodro-bimisho.service';

@Component({
    selector: 'insutech-khodro-bimisho-delete-dialog',
    templateUrl: './khodro-bimisho-delete-dialog.component.html'
})
export class KhodroBimishoDeleteDialogComponent {
    khodro: IKhodroBimisho;

    constructor(
        protected khodroService: KhodroBimishoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.khodroService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'khodroListModification',
                content: 'Deleted an khodro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-khodro-bimisho-delete-popup',
    template: ''
})
export class KhodroBimishoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khodro }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KhodroBimishoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.khodro = khodro;
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
