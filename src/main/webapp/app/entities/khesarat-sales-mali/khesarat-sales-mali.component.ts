import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IKhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { KhesaratSalesMaliService } from './khesarat-sales-mali.service';

@Component({
    selector: 'jhi-khesarat-sales-mali',
    templateUrl: './khesarat-sales-mali.component.html'
})
export class KhesaratSalesMaliComponent implements OnInit, OnDestroy {
    khesaratSalesMalis: IKhesaratSalesMali[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    reverse: any;
    totalItems: number;
    error: any;
    success: any;

    constructor(
        protected khesaratSalesMaliService: KhesaratSalesMaliService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected parseLinks: JhiParseLinks,
        protected accountService: AccountService
    ) {
        this.khesaratSalesMalis = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
    }

    setActive(khesaratSalesMali, isActivated) {
        khesaratSalesMali.faal = isActivated;
        this.khesaratSalesMaliService.update(khesaratSalesMali).subscribe(response => {
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
        this.khesaratSalesMaliService
            .query({
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IKhesaratSalesMali[]>) => this.paginateKhesaratSalesMalis(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    reset() {
        this.page = 0;
        this.khesaratSalesMalis = [];
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
        this.registerChangeInKhesaratSalesMalis();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IKhesaratSalesMali) {
        return item.id;
    }

    registerChangeInKhesaratSalesMalis() {
        this.eventSubscriber = this.eventManager.subscribe('khesaratSalesMaliListModification', response => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateKhesaratSalesMalis(data: IKhesaratSalesMali[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.khesaratSalesMalis.push(data[i]);
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
