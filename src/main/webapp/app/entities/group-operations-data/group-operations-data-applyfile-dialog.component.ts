import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroupOperationsData } from 'app/shared/model/group-operations-data.model';
import { GroupOperationsDataService } from './group-operations-data.service';

@Component({
    selector: 'jhi-group-operations-data-applyfile-dialog',
    templateUrl: './group-operations-data-applyfile-dialog.component.html'
})
export class GroupOperationsDataApplyFileDialogComponent {
    groupOperationsData: IGroupOperationsData;

    constructor(
        protected groupOperationsDataService: GroupOperationsDataService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmApplyFile(id: number) {
        this.groupOperationsDataService.applyfile(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'groupOperationsDataListModification',
                content: 'ApplyFile an groupOperationsData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-group-operations-data-applyfile-popup',
    template: ''
})
export class GroupOperationsDataApplyFilePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ groupOperationsData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GroupOperationsDataApplyFileDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.groupOperationsData = groupOperationsData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/group-operations-data', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/group-operations-data', { outlets: { popup: null } }]);
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
