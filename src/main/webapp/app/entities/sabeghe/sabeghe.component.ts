import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ISabeghe } from 'app/shared/model/sabeghe.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { SabegheService } from './sabeghe.service';

@Component({
    selector: 'jhi-sabeghe',
    templateUrl: './sabeghe.component.html'
})
export class SabegheComponent implements OnInit, OnDestroy {
    sabeghes: ISabeghe[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    error: any;
    success: any;
    predicate: any;
    reverse: any;
    totalItems: number;
    searchvalue: any;
    propkey: string;
    operation: string;
    entity: any;
    searchparams: any[] = [];

    constructor(
        protected sabegheService: SabegheService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected parseLinks: JhiParseLinks,
        protected accountService: AccountService
    ) {
        this.sabeghes = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
    }

    setActive(sabeghe, isActivated) {
        sabeghe.faal = isActivated;
        this.sabegheService.update(sabeghe).subscribe(response => {
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
        this.sabegheService
            .query({
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ISabeghe[]>) => this.paginateSabeghes(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    reset() {
        this.page = 0;
        this.sabeghes = [];
        this.searchparams = [];
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
        this.registerChangeInSabeghes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISabeghe) {
        return item.id;
    }

    registerChangeInSabeghes() {
        this.eventSubscriber = this.eventManager.subscribe('sabegheListModification', response => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }
    //search
    search() {
        this.searchparams.push(this.propkey);
        this.searchparams.push(this.operation);
        this.searchparams.push(this.searchvalue);
        if (this.searchvalue != null) {
            this.sabegheService
                .search(this.searchparams, {
                    page: this.page,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe((res: HttpResponse<ISabeghe[]>) => {
                    //  console.log('red', res);
                    this.searchparams = [];
                    if (res.body) {
                        this.sabeghes = [];
                    }
                    this.paginateSabeghes(res.body, res.headers), (res: HttpErrorResponse) => this.onError(res.message);
                });
        } else {
            this.reset();
        }
    }

    clear() {
        this.page = 0;
        this.searchvalue = null;
        this.searchparams = [];
        this.sabeghes = [];
        this.propkey = null;
        this.operation = null;
        // this.router.navigate([
        //     '/points',
        //     {
        //         page: this.page,
        //         sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        //     }
        // ]);
        this.loadAll();
    }

    protected paginateSabeghes(data: ISabeghe[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.sabeghes.push(data[i]);
        }
        if (this.sabeghes.length > 0) {
            this.entity = Object.keys(this.sabeghes[0]);
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
