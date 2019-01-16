import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';
import { SherkatBimeBimishoService } from './sherkat-bime-bimisho.service';

@Component({
    selector: 'insutech-sherkat-bime-bimisho-delete-dialog',
    templateUrl: './sherkat-bime-bimisho-delete-dialog.component.html'
})
export class SherkatBimeBimishoDeleteDialogComponent {
    sherkatBime: ISherkatBimeBimisho;

    constructor(
        protected sherkatBimeService: SherkatBimeBimishoService,
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
    selector: 'insutech-sherkat-bime-bimisho-delete-popup',
    template: ''
})
export class SherkatBimeBimishoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sherkatBime }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SherkatBimeBimishoDeleteDialogComponent as Component, {
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
