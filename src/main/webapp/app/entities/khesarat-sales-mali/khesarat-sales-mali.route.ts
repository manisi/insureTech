import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';
import { KhesaratSalesMaliService } from './khesarat-sales-mali.service';
import { KhesaratSalesMaliComponent } from './khesarat-sales-mali.component';
import { KhesaratSalesMaliDetailComponent } from './khesarat-sales-mali-detail.component';
import { KhesaratSalesMaliUpdateComponent } from './khesarat-sales-mali-update.component';
import { KhesaratSalesMaliDeletePopupComponent } from './khesarat-sales-mali-delete-dialog.component';
import { IKhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';

@Injectable({ providedIn: 'root' })
export class KhesaratSalesMaliResolve implements Resolve<IKhesaratSalesMali> {
    constructor(private service: KhesaratSalesMaliService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKhesaratSalesMali> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KhesaratSalesMali>) => response.ok),
                map((khesaratSalesMali: HttpResponse<KhesaratSalesMali>) => khesaratSalesMali.body)
            );
        }
        return of(new KhesaratSalesMali());
    }
}

export const khesaratSalesMaliRoute: Routes = [
    {
        path: '',
        component: KhesaratSalesMaliComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: KhesaratSalesMaliDetailComponent,
        resolve: {
            khesaratSalesMali: KhesaratSalesMaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: KhesaratSalesMaliUpdateComponent,
        resolve: {
            khesaratSalesMali: KhesaratSalesMaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: KhesaratSalesMaliUpdateComponent,
        resolve: {
            khesaratSalesMali: KhesaratSalesMaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const khesaratSalesMaliPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: KhesaratSalesMaliDeletePopupComponent,
        resolve: {
            khesaratSalesMali: KhesaratSalesMaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
