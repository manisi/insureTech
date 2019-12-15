import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';
import { AccountService } from 'app/core';
import { SabegheKhesaratService } from './sabeghe-khesarat.service';

@Component({
    selector: 'jhi-sabeghe-khesarat',
    templateUrl: './sabeghe-khesarat.component.html'
})
export class SabegheKhesaratComponent implements OnInit, OnDestroy {
    sabegheKhesarats: ISabegheKhesarat[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sabegheKhesaratService: SabegheKhesaratService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sabegheKhesaratService
            .query()
            .pipe(
                filter((res: HttpResponse<ISabegheKhesarat[]>) => res.ok),
                map((res: HttpResponse<ISabegheKhesarat[]>) => res.body)
            )
            .subscribe(
                (res: ISabegheKhesarat[]) => {
                    this.sabegheKhesarats = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSabegheKhesarats();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISabegheKhesarat) {
        return item.id;
    }

    registerChangeInSabegheKhesarats() {
        this.eventSubscriber = this.eventManager.subscribe('sabegheKhesaratListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
