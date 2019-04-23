import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MoredEstefadeSales } from 'app/shared/model/mored-estefade-sales.model';
import { MoredEstefadeSalesService } from './mored-estefade-sales.service';
import { MoredEstefadeSalesComponent } from './mored-estefade-sales.component';
import { MoredEstefadeSalesDetailComponent } from './mored-estefade-sales-detail.component';
import { MoredEstefadeSalesUpdateComponent } from './mored-estefade-sales-update.component';
import { MoredEstefadeSalesDeletePopupComponent } from './mored-estefade-sales-delete-dialog.component';
import { IMoredEstefadeSales } from 'app/shared/model/mored-estefade-sales.model';

@Injectable({ providedIn: 'root' })
export class MoredEstefadeSalesResolve implements Resolve<IMoredEstefadeSales> {
    constructor(private service: MoredEstefadeSalesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMoredEstefadeSales> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MoredEstefadeSales>) => response.ok),
                map((moredEstefadeSales: HttpResponse<MoredEstefadeSales>) => moredEstefadeSales.body)
            );
        }
        return of(new MoredEstefadeSales());
    }
}

export const moredEstefadeSalesRoute: Routes = [
    {
        path: '',
        component: MoredEstefadeSalesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.moredEstefadeSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: MoredEstefadeSalesDetailComponent,
        resolve: {
            moredEstefadeSales: MoredEstefadeSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.moredEstefadeSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: MoredEstefadeSalesUpdateComponent,
        resolve: {
            moredEstefadeSales: MoredEstefadeSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.moredEstefadeSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: MoredEstefadeSalesUpdateComponent,
        resolve: {
            moredEstefadeSales: MoredEstefadeSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.moredEstefadeSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const moredEstefadeSalesPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: MoredEstefadeSalesDeletePopupComponent,
        resolve: {
            moredEstefadeSales: MoredEstefadeSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.moredEstefadeSales.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
