import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IModateBimename } from 'app/shared/model/modate-bimename.model';
import { AccountService } from 'app/core';
import { ModateBimenameService } from './modate-bimename.service';

@Component({
    selector: 'jhi-modate-bimename',
    templateUrl: './modate-bimename.component.html'
})
export class ModateBimenameComponent implements OnInit, OnDestroy {
    modateBimenames: IModateBimename[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected modateBimenameService: ModateBimenameService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.modateBimenameService
            .query()
            .pipe(
                filter((res: HttpResponse<IModateBimename[]>) => res.ok),
                map((res: HttpResponse<IModateBimename[]>) => res.body)
            )
            .subscribe(
                (res: IModateBimename[]) => {
                    this.modateBimenames = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInModateBimenames();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IModateBimename) {
        return item.id;
    }

    registerChangeInModateBimenames() {
        this.eventSubscriber = this.eventManager.subscribe('modateBimenameListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
