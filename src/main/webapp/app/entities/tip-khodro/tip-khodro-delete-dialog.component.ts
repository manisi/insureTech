import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipKhodro } from 'app/shared/model/tip-khodro.model';
import { TipKhodroService } from './tip-khodro.service';

@Component({
    selector: 'jhi-tip-khodro-delete-dialog',
    templateUrl: './tip-khodro-delete-dialog.component.html'
})
export class TipKhodroDeleteDialogComponent {
    tipKhodro: ITipKhodro;

    constructor(
        protected tipKhodroService: TipKhodroService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipKhodroService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipKhodroListModification',
                content: 'Deleted an tipKhodro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tip-khodro-delete-popup',
    template: ''
})
export class TipKhodroDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipKhodro }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipKhodroDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tipKhodro = tipKhodro;
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
