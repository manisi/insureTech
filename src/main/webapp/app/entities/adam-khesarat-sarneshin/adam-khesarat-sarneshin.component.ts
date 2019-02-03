import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IAdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { AdamKhesaratSarneshinService } from './adam-khesarat-sarneshin.service';

@Component({
    selector: 'jhi-adam-khesarat-sarneshin',
    templateUrl: './adam-khesarat-sarneshin.component.html'
})
export class AdamKhesaratSarneshinComponent implements OnInit, OnDestroy {
    adamKhesaratSarneshins: IAdamKhesaratSarneshin[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    error: any;
    success: any;
    page: any;
    predicate: any;
    reverse: any;
    totalItems: number;

    constructor(
        protected adamKhesaratSarneshinService: AdamKhesaratSarneshinService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected parseLinks: JhiParseLinks,
        protected accountService: AccountService
    ) {
        this.adamKhesaratSarneshins = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
    }

    setActive(adamKhesaratSarneshin, isActivated) {
        adamKhesaratSarneshin.faal = isActivated;
        this.adamKhesaratSarneshinService.update(adamKhesaratSarneshin).subscribe(response => {
            if (response.status === 200) {
                this.error = null;
                this.success = 'OK';
                // this.loadAll();
            } else {
                this.success = null;
                this.error = 'ERROR';
            }
        });
    }

    loadAll() {
        this.adamKhesaratSarneshinService
            .query({
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IAdamKhesaratSarneshin[]>) => this.paginateAdamKhesaratSarneshins(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    reset() {
        this.page = 0;
        this.adamKhesaratSarneshins = [];
        this.loadAll();
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAdamKhesaratSarneshins();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAdamKhesaratSarneshin) {
        return item.id;
    }

    registerChangeInAdamKhesaratSarneshins() {
        this.eventSubscriber = this.eventManager.subscribe('adamKhesaratSarneshinListModification', response => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateAdamKhesaratSarneshins(data: IAdamKhesaratSarneshin[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.adamKhesaratSarneshins.push(data[i]);
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
