import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IAdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { AdamKhesaratBadaneService } from './adam-khesarat-badane.service';

@Component({
    selector: 'jhi-adam-khesarat-badane',
    templateUrl: './adam-khesarat-badane.component.html'
})
export class AdamKhesaratBadaneComponent implements OnInit, OnDestroy {
    adamKhesaratBadanes: IAdamKhesaratBadane[];
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
        protected adamKhesaratBadaneService: AdamKhesaratBadaneService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected parseLinks: JhiParseLinks,
        protected accountService: AccountService
    ) {
        this.adamKhesaratBadanes = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
    }

    setActive(adamKhesaratBadane, isActivated) {
        adamKhesaratBadane.faal = isActivated;
        this.adamKhesaratBadaneService.update(adamKhesaratBadane).subscribe(response => {
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
        this.adamKhesaratBadaneService
            .query({
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IAdamKhesaratBadane[]>) => this.paginateAdamKhesaratBadanes(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    reset() {
        this.page = 0;
        this.adamKhesaratBadanes = [];
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
        this.registerChangeInAdamKhesaratBadanes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAdamKhesaratBadane) {
        return item.id;
    }

    registerChangeInAdamKhesaratBadanes() {
        this.eventSubscriber = this.eventManager.subscribe('adamKhesaratBadaneListModification', response => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateAdamKhesaratBadanes(data: IAdamKhesaratBadane[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.adamKhesaratBadanes.push(data[i]);
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
