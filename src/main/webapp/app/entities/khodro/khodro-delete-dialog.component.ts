import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKhodro } from 'app/shared/model/khodro.model';
import { KhodroService } from './khodro.service';

@Component({
    selector: 'insutech-khodro-delete-dialog',
    templateUrl: './khodro-delete-dialog.component.html'
})
export class KhodroDeleteDialogComponent {
    khodro: IKhodro;

    constructor(protected khodroService: KhodroService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.khodroService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'khodroListModification',
                content: 'Deleted an khodro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-khodro-delete-popup',
    template: ''
})
export class KhodroDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khodro }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KhodroDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.khodro = khodro;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/khodro', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/khodro', { outlets: { popup: null } }]);
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
