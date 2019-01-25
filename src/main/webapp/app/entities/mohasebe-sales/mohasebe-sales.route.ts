import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MohasebeSales } from 'app/shared/model/mohasebe-sales.model';
import { MohasebeSalesService } from './mohasebe-sales.service';
import { MohasebeSalesComponent } from './mohasebe-sales.component';
import { MohasebeSalesDetailComponent } from './mohasebe-sales-detail.component';
import { MohasebeSalesUpdateComponent } from './mohasebe-sales-update.component';
import { MohasebeSalesDeletePopupComponent } from './mohasebe-sales-delete-dialog.component';
import { IMohasebeSales } from 'app/shared/model/mohasebe-sales.model';

@Injectable({ providedIn: 'root' })
export class MohasebeSalesResolve implements Resolve<IMohasebeSales> {
    constructor(private service: MohasebeSalesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMohasebeSales> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MohasebeSales>) => response.ok),
                map((mohasebeSales: HttpResponse<MohasebeSales>) => mohasebeSales.body)
            );
        }
        return of(new MohasebeSales());
    }
}

export const mohasebeSalesRoute: Routes = [
    {
        path: '',
        component: MohasebeSalesComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.mohasebeSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: MohasebeSalesDetailComponent,
        resolve: {
            mohasebeSales: MohasebeSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.mohasebeSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: MohasebeSalesUpdateComponent,
        resolve: {
            mohasebeSales: MohasebeSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.mohasebeSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: MohasebeSalesUpdateComponent,
        resolve: {
            mohasebeSales: MohasebeSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.mohasebeSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mohasebeSalesPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: MohasebeSalesDeletePopupComponent,
        resolve: {
            mohasebeSales: MohasebeSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.mohasebeSales.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
