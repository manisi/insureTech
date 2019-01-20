import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from './sherkat-bime.service';

@Component({
    selector: 'insutech-sherkat-bime-delete-dialog',
    templateUrl: './sherkat-bime-delete-dialog.component.html'
})
export class SherkatBimeDeleteDialogComponent {
    sherkatBime: ISherkatBime;

    constructor(
        protected sherkatBimeService: SherkatBimeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sherkatBimeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sherkatBimeListModification',
                content: 'Deleted an sherkatBime'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-sherkat-bime-delete-popup',
    template: ''
})
export class SherkatBimeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sherkatBime }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SherkatBimeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sherkatBime = sherkatBime;
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
