import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModateBimename } from 'app/shared/model/modate-bimename.model';
import { ModateBimenameService } from './modate-bimename.service';

@Component({
    selector: 'jhi-modate-bimename-delete-dialog',
    templateUrl: './modate-bimename-delete-dialog.component.html'
})
export class ModateBimenameDeleteDialogComponent {
    modateBimename: IModateBimename;

    constructor(
        protected modateBimenameService: ModateBimenameService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.modateBimenameService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'modateBimenameListModification',
                content: 'Deleted an modateBimename'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-modate-bimename-delete-popup',
    template: ''
})
export class ModateBimenameDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ modateBimename }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ModateBimenameDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.modateBimename = modateBimename;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/modate-bimename', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/modate-bimename', { outlets: { popup: null } }]);
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
