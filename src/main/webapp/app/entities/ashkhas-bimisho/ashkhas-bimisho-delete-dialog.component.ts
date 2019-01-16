import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAshkhasBimisho } from 'app/shared/model/ashkhas-bimisho.model';
import { AshkhasBimishoService } from './ashkhas-bimisho.service';

@Component({
    selector: 'insutech-ashkhas-bimisho-delete-dialog',
    templateUrl: './ashkhas-bimisho-delete-dialog.component.html'
})
export class AshkhasBimishoDeleteDialogComponent {
    ashkhas: IAshkhasBimisho;

    constructor(
        protected ashkhasService: AshkhasBimishoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ashkhasService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ashkhasListModification',
                content: 'Deleted an ashkhas'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-ashkhas-bimisho-delete-popup',
    template: ''
})
export class AshkhasBimishoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ashkhas }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AshkhasBimishoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.ashkhas = ashkhas;
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
