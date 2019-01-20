import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAshkhas } from 'app/shared/model/ashkhas.model';
import { AshkhasService } from './ashkhas.service';

@Component({
    selector: 'insutech-ashkhas-delete-dialog',
    templateUrl: './ashkhas-delete-dialog.component.html'
})
export class AshkhasDeleteDialogComponent {
    ashkhas: IAshkhas;

    constructor(protected ashkhasService: AshkhasService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

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
    selector: 'insutech-ashkhas-delete-popup',
    template: ''
})
export class AshkhasDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ashkhas }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AshkhasDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
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
