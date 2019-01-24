import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';
import { NoeSabegheService } from './noe-sabeghe.service';

@Component({
    selector: 'jhi-noe-sabeghe-delete-dialog',
    templateUrl: './noe-sabeghe-delete-dialog.component.html'
})
export class NoeSabegheDeleteDialogComponent {
    noeSabeghe: INoeSabeghe;

    constructor(
        protected noeSabegheService: NoeSabegheService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.noeSabegheService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'noeSabegheListModification',
                content: 'Deleted an noeSabeghe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-noe-sabeghe-delete-popup',
    template: ''
})
export class NoeSabegheDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ noeSabeghe }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NoeSabegheDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.noeSabeghe = noeSabeghe;
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
