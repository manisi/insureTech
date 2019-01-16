import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ISherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { SherkatBimeBimishoService } from './sherkat-bime-bimisho.service';

@Component({
    selector: 'insutech-sherkat-bime-bimisho',
    templateUrl: './sherkat-bime-bimisho.component.html'
})
export class SherkatBimeBimishoComponent implements OnInit, OnDestroy {
    currentAccount: any;
    sherkatBimes: ISherkatBimeBimisho[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        protected sherkatBimeService: SherkatBimeBimishoService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.sherkatBimeService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ISherkatBimeBimisho[]>) => this.paginateSherkatBimes(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/sherkat-bime-bimisho'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/sherkat-bime-bimisho',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSherkatBimes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISherkatBimeBimisho) {
        return item.id;
    }

    registerChangeInSherkatBimes() {
        this.eventSubscriber = this.eventManager.subscribe('sherkatBimeListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateSherkatBimes(data: ISherkatBimeBimisho[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.sherkatBimes = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
