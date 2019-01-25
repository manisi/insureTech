import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKohnegi } from 'app/shared/model/kohnegi.model';
import { KohnegiService } from './kohnegi.service';

@Component({
    selector: 'jhi-kohnegi-delete-dialog',
    templateUrl: './kohnegi-delete-dialog.component.html'
})
export class KohnegiDeleteDialogComponent {
    kohnegi: IKohnegi;

    constructor(protected kohnegiService: KohnegiService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.kohnegiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'kohnegiListModification',
                content: 'Deleted an kohnegi'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-kohnegi-delete-popup',
    template: ''
})
export class KohnegiDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ kohnegi }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KohnegiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.kohnegi = kohnegi;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/kohnegi', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/kohnegi', { outlets: { popup: null } }]);
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
