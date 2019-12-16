import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';
import { TakhfifTavafoghiService } from './takhfif-tavafoghi.service';

@Component({
    selector: 'jhi-takhfif-tavafoghi-delete-dialog',
    templateUrl: './takhfif-tavafoghi-delete-dialog.component.html'
})
export class TakhfifTavafoghiDeleteDialogComponent {
    takhfifTavafoghi: ITakhfifTavafoghi;

    constructor(
        protected takhfifTavafoghiService: TakhfifTavafoghiService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.takhfifTavafoghiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'takhfifTavafoghiListModification',
                content: 'Deleted an takhfifTavafoghi'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-takhfif-tavafoghi-delete-popup',
    template: ''
})
export class TakhfifTavafoghiDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ takhfifTavafoghi }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TakhfifTavafoghiDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.takhfifTavafoghi = takhfifTavafoghi;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/takhfif-tavafoghi', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/takhfif-tavafoghi', { outlets: { popup: null } }]);
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
