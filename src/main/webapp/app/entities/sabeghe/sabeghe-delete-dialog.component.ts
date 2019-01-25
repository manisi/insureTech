import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from './sabeghe.service';

@Component({
    selector: 'jhi-sabeghe-delete-dialog',
    templateUrl: './sabeghe-delete-dialog.component.html'
})
export class SabegheDeleteDialogComponent {
    sabeghe: ISabeghe;

    constructor(protected sabegheService: SabegheService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sabegheService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sabegheListModification',
                content: 'Deleted an sabeghe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sabeghe-delete-popup',
    template: ''
})
export class SabegheDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sabeghe }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SabegheDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sabeghe = sabeghe;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/sabeghe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/sabeghe', { outlets: { popup: null } }]);
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
