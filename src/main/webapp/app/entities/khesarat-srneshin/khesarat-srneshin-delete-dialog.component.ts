import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKhesaratSrneshin } from 'app/shared/model/khesarat-srneshin.model';
import { KhesaratSrneshinService } from './khesarat-srneshin.service';

@Component({
    selector: 'jhi-khesarat-srneshin-delete-dialog',
    templateUrl: './khesarat-srneshin-delete-dialog.component.html'
})
export class KhesaratSrneshinDeleteDialogComponent {
    khesaratSrneshin: IKhesaratSrneshin;

    constructor(
        protected khesaratSrneshinService: KhesaratSrneshinService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.khesaratSrneshinService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'khesaratSrneshinListModification',
                content: 'Deleted an khesaratSrneshin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-khesarat-srneshin-delete-popup',
    template: ''
})
export class KhesaratSrneshinDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khesaratSrneshin }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KhesaratSrneshinDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.khesaratSrneshin = khesaratSrneshin;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/khesarat-srneshin', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/khesarat-srneshin', { outlets: { popup: null } }]);
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
