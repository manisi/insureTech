import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVaziatBime } from 'app/shared/model/vaziat-bime.model';
import { VaziatBimeService } from './vaziat-bime.service';

@Component({
    selector: 'jhi-vaziat-bime-delete-dialog',
    templateUrl: './vaziat-bime-delete-dialog.component.html'
})
export class VaziatBimeDeleteDialogComponent {
    vaziatBime: IVaziatBime;

    constructor(
        protected vaziatBimeService: VaziatBimeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vaziatBimeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vaziatBimeListModification',
                content: 'Deleted an vaziatBime'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vaziat-bime-delete-popup',
    template: ''
})
export class VaziatBimeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vaziatBime }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VaziatBimeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.vaziatBime = vaziatBime;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/vaziat-bime', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/vaziat-bime', { outlets: { popup: null } }]);
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
