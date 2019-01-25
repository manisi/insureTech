import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPooshesh } from 'app/shared/model/pooshesh.model';
import { PoosheshService } from './pooshesh.service';

@Component({
    selector: 'jhi-pooshesh-delete-dialog',
    templateUrl: './pooshesh-delete-dialog.component.html'
})
export class PoosheshDeleteDialogComponent {
    pooshesh: IPooshesh;

    constructor(protected poosheshService: PoosheshService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

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
    selector: 'jhi-pooshesh-delete-popup',
    template: ''
})
export class PoosheshDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pooshesh }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PoosheshDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
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
