import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';
import { SabegheKhesaratService } from './sabeghe-khesarat.service';

@Component({
    selector: 'jhi-sabeghe-khesarat-delete-dialog',
    templateUrl: './sabeghe-khesarat-delete-dialog.component.html'
})
export class SabegheKhesaratDeleteDialogComponent {
    sabegheKhesarat: ISabegheKhesarat;

    constructor(
        protected sabegheKhesaratService: SabegheKhesaratService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sabegheKhesaratService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sabegheKhesaratListModification',
                content: 'Deleted an sabegheKhesarat'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sabeghe-khesarat-delete-popup',
    template: ''
})
export class SabegheKhesaratDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sabegheKhesarat }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SabegheKhesaratDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sabegheKhesarat = sabegheKhesarat;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/sabeghe-khesarat', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/sabeghe-khesarat', { outlets: { popup: null } }]);
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
