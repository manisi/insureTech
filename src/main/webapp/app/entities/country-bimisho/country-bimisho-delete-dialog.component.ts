import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICountryBimisho } from 'app/shared/model/country-bimisho.model';
import { CountryBimishoService } from './country-bimisho.service';

@Component({
    selector: 'insutech-country-bimisho-delete-dialog',
    templateUrl: './country-bimisho-delete-dialog.component.html'
})
export class CountryBimishoDeleteDialogComponent {
    country: ICountryBimisho;

    constructor(
        protected countryService: CountryBimishoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.countryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'countryListModification',
                content: 'Deleted an country'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'insutech-country-bimisho-delete-popup',
    template: ''
})
export class CountryBimishoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ country }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CountryBimishoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.country = country;
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
