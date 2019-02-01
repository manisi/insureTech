import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAnvaeKhodro } from 'app/shared/model/anvae-khodro.model';
import { AnvaeKhodroService } from './anvae-khodro.service';

@Component({
    selector: 'jhi-anvae-khodro-delete-dialog',
    templateUrl: './anvae-khodro-delete-dialog.component.html'
})
export class AnvaeKhodroDeleteDialogComponent {
    anvaeKhodro: IAnvaeKhodro;

    constructor(
        protected anvaeKhodroService: AnvaeKhodroService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.anvaeKhodroService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'anvaeKhodroListModification',
                content: 'Deleted an anvaeKhodro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-anvae-khodro-delete-popup',
    template: ''
})
export class AnvaeKhodroDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ anvaeKhodro }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AnvaeKhodroDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.anvaeKhodro = anvaeKhodro;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/anvae-khodro', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/anvae-khodro', { outlets: { popup: null } }]);
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
