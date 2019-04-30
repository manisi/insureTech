import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOnvanKhodro } from 'app/shared/model/onvan-khodro.model';
import { OnvanKhodroService } from './onvan-khodro.service';

@Component({
    selector: 'jhi-onvan-khodro-delete-dialog',
    templateUrl: './onvan-khodro-delete-dialog.component.html'
})
export class OnvanKhodroDeleteDialogComponent {
    onvanKhodro: IOnvanKhodro;

    constructor(
        protected onvanKhodroService: OnvanKhodroService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.onvanKhodroService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'onvanKhodroListModification',
                content: 'Deleted an onvanKhodro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-onvan-khodro-delete-popup',
    template: ''
})
export class OnvanKhodroDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ onvanKhodro }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OnvanKhodroDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.onvanKhodro = onvanKhodro;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/onvan-khodro', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/onvan-khodro', { outlets: { popup: null } }]);
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
