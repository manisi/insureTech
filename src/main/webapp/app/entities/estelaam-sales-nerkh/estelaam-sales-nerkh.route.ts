import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';
import { EstelaamSalesNerkhService } from './estelaam-sales-nerkh.service';
import { EstelaamSalesNerkhComponent } from './estelaam-sales-nerkh.component';
import { EstelaamSalesNerkhDetailComponent } from './estelaam-sales-nerkh-detail.component';
import { EstelaamSalesNerkhUpdateComponent } from './estelaam-sales-nerkh-update.component';
import { EstelaamSalesNerkhDeletePopupComponent } from './estelaam-sales-nerkh-delete-dialog.component';
import { IEstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';

@Injectable({ providedIn: 'root' })
export class EstelaamSalesNerkhResolve implements Resolve<IEstelaamSalesNerkh> {
    constructor(private service: EstelaamSalesNerkhService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEstelaamSalesNerkh> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EstelaamSalesNerkh>) => response.ok),
                map((estelaamSalesNerkh: HttpResponse<EstelaamSalesNerkh>) => estelaamSalesNerkh.body)
            );
        }
        return of(new EstelaamSalesNerkh());
    }
}

export const estelaamSalesNerkhRoute: Routes = [
    {
        path: '',
        component: EstelaamSalesNerkhComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.estelaamSalesNerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EstelaamSalesNerkhDetailComponent,
        resolve: {
            estelaamSalesNerkh: EstelaamSalesNerkhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.estelaamSalesNerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EstelaamSalesNerkhUpdateComponent,
        resolve: {
            estelaamSalesNerkh: EstelaamSalesNerkhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.estelaamSalesNerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EstelaamSalesNerkhUpdateComponent,
        resolve: {
            estelaamSalesNerkh: EstelaamSalesNerkhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.estelaamSalesNerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const estelaamSalesNerkhPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EstelaamSalesNerkhDeletePopupComponent,
        resolve: {
            estelaamSalesNerkh: EstelaamSalesNerkhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.estelaamSalesNerkh.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
