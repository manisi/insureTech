import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISaalSakht } from 'app/shared/model/saal-sakht.model';
import { SaalSakhtService } from './saal-sakht.service';

@Component({
    selector: 'jhi-saal-sakht-delete-dialog',
    templateUrl: './saal-sakht-delete-dialog.component.html'
})
export class SaalSakhtDeleteDialogComponent {
    saalSakht: ISaalSakht;

    constructor(
        protected saalSakhtService: SaalSakhtService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.saalSakhtService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'saalSakhtListModification',
                content: 'Deleted an saalSakht'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-saal-sakht-delete-popup',
    template: ''
})
export class SaalSakhtDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ saalSakht }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SaalSakhtDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.saalSakht = saalSakht;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/saal-sakht', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/saal-sakht', { outlets: { popup: null } }]);
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
