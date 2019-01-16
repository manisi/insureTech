import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPoosheshBimisho } from 'app/shared/model/pooshesh-bimisho.model';
import { PoosheshBimishoService } from './pooshesh-bimisho.service';

@Component({
    selector: 'insutech-pooshesh-bimisho-delete-dialog',
    templateUrl: './pooshesh-bimisho-delete-dialog.component.html'
})
export class PoosheshBimishoDeleteDialogComponent {
    pooshesh: IPoosheshBimisho;

    constructor(
        protected poosheshService: PoosheshBimishoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.poosheshService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'poosheshListModification',
                content: 'Deleted an pooshesh'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-pooshesh-bimisho-delete-popup',
    template: ''
})
export class PoosheshBimishoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pooshesh }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PoosheshBimishoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pooshesh = pooshesh;
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
