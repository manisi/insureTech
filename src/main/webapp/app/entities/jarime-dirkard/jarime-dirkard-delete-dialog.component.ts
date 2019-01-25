import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJarimeDirkard } from 'app/shared/model/jarime-dirkard.model';
import { JarimeDirkardService } from './jarime-dirkard.service';

@Component({
    selector: 'jhi-jarime-dirkard-delete-dialog',
    templateUrl: './jarime-dirkard-delete-dialog.component.html'
})
export class JarimeDirkardDeleteDialogComponent {
    jarimeDirkard: IJarimeDirkard;

    constructor(
        protected jarimeDirkardService: JarimeDirkardService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jarimeDirkardService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jarimeDirkardListModification',
                content: 'Deleted an jarimeDirkard'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-jarime-dirkard-delete-popup',
    template: ''
})
export class JarimeDirkardDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jarimeDirkard }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JarimeDirkardDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.jarimeDirkard = jarimeDirkard;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/jarime-dirkard', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/jarime-dirkard', { outlets: { popup: null } }]);
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
