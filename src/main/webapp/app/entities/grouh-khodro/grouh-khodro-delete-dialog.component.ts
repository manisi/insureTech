import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from './grouh-khodro.service';

@Component({
    selector: 'jhi-grouh-khodro-delete-dialog',
    templateUrl: './grouh-khodro-delete-dialog.component.html'
})
export class GrouhKhodroDeleteDialogComponent {
    grouhKhodro: IGrouhKhodro;

    constructor(
        protected grouhKhodroService: GrouhKhodroService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.grouhKhodroService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'grouhKhodroListModification',
                content: 'Deleted an grouhKhodro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-grouh-khodro-delete-popup',
    template: ''
})
export class GrouhKhodroDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ grouhKhodro }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GrouhKhodroDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.grouhKhodro = grouhKhodro;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/grouh-khodro', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/grouh-khodro', { outlets: { popup: null } }]);
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
