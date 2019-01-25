import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KhesaratSales } from 'app/shared/model/khesarat-sales.model';
import { KhesaratSalesService } from './khesarat-sales.service';
import { KhesaratSalesComponent } from './khesarat-sales.component';
import { KhesaratSalesDetailComponent } from './khesarat-sales-detail.component';
import { KhesaratSalesUpdateComponent } from './khesarat-sales-update.component';
import { KhesaratSalesDeletePopupComponent } from './khesarat-sales-delete-dialog.component';
import { IKhesaratSales } from 'app/shared/model/khesarat-sales.model';

@Injectable({ providedIn: 'root' })
export class KhesaratSalesResolve implements Resolve<IKhesaratSales> {
    constructor(private service: KhesaratSalesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKhesaratSales> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KhesaratSales>) => response.ok),
                map((khesaratSales: HttpResponse<KhesaratSales>) => khesaratSales.body)
            );
        }
        return of(new KhesaratSales());
    }
}

export const khesaratSalesRoute: Routes = [
    {
        path: '',
        component: KhesaratSalesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: KhesaratSalesDetailComponent,
        resolve: {
            khesaratSales: KhesaratSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: KhesaratSalesUpdateComponent,
        resolve: {
            khesaratSales: KhesaratSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: KhesaratSalesUpdateComponent,
        resolve: {
            khesaratSales: KhesaratSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSales.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const khesaratSalesPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: KhesaratSalesDeletePopupComponent,
        resolve: {
            khesaratSales: KhesaratSalesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSales.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
