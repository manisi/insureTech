import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IAdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { AdamKhesaratSalesMaliService } from './adam-khesarat-sales-mali.service';

@Component({
    selector: 'jhi-adam-khesarat-sales-mali',
    templateUrl: './adam-khesarat-sales-mali.component.html'
})
export class AdamKhesaratSalesMaliComponent implements OnInit, OnDestroy {
    adamKhesaratSalesMalis: IAdamKhesaratSalesMali[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    reverse: any;
    error: any;
    success: any;
    totalItems: number;

    constructor(
        protected adamKhesaratSalesMaliService: AdamKhesaratSalesMaliService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected parseLinks: JhiParseLinks,
        protected accountService: AccountService
    ) {
        this.adamKhesaratSalesMalis = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
    }

    setActive(adamKhesaratSalesMali, isActivated) {
        adamKhesaratSalesMali.faal = isActivated;
        this.adamKhesaratSalesMaliService.update(adamKhesaratSalesMali).subscribe(response => {
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
        this.adamKhesaratSalesMaliService
            .query({
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IAdamKhesaratSalesMali[]>) => this.paginateAdamKhesaratSalesMalis(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    reset() {
        this.page = 0;
        this.adamKhesaratSalesMalis = [];
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
        this.registerChangeInAdamKhesaratSalesMalis();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAdamKhesaratSalesMali) {
        return item.id;
    }

    registerChangeInAdamKhesaratSalesMalis() {
        this.eventSubscriber = this.eventManager.subscribe('adamKhesaratSalesMaliListModification', response => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateAdamKhesaratSalesMalis(data: IAdamKhesaratSalesMali[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.adamKhesaratSalesMalis.push(data[i]);
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
