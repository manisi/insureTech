import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';
import { AdamKhesaratSarneshinService } from './adam-khesarat-sarneshin.service';

@Component({
    selector: 'jhi-adam-khesarat-sarneshin-delete-dialog',
    templateUrl: './adam-khesarat-sarneshin-delete-dialog.component.html'
})
export class AdamKhesaratSarneshinDeleteDialogComponent {
    adamKhesaratSarneshin: IAdamKhesaratSarneshin;

    constructor(
        protected adamKhesaratSarneshinService: AdamKhesaratSarneshinService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adamKhesaratSarneshinService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'adamKhesaratSarneshinListModification',
                content: 'Deleted an adamKhesaratSarneshin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-adam-khesarat-sarneshin-delete-popup',
    template: ''
})
export class AdamKhesaratSarneshinDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adamKhesaratSarneshin }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdamKhesaratSarneshinDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.adamKhesaratSarneshin = adamKhesaratSarneshin;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/adam-khesarat-sarneshin', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/adam-khesarat-sarneshin', { outlets: { popup: null } }]);
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
