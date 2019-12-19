import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';
import { AdamKhesaratSalesMaliService } from './adam-khesarat-sales-mali.service';
import { AdamKhesaratSalesMaliComponent } from './adam-khesarat-sales-mali.component';
import { AdamKhesaratSalesMaliDetailComponent } from './adam-khesarat-sales-mali-detail.component';
import { AdamKhesaratSalesMaliUpdateComponent } from './adam-khesarat-sales-mali-update.component';
import { AdamKhesaratSalesMaliDeletePopupComponent } from './adam-khesarat-sales-mali-delete-dialog.component';
import { IAdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';

@Injectable({ providedIn: 'root' })
export class AdamKhesaratSalesMaliResolve implements Resolve<IAdamKhesaratSalesMali> {
    constructor(private service: AdamKhesaratSalesMaliService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAdamKhesaratSalesMali> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AdamKhesaratSalesMali>) => response.ok),
                map((adamKhesaratSalesMali: HttpResponse<AdamKhesaratSalesMali>) => adamKhesaratSalesMali.body)
            );
        }
        return of(new AdamKhesaratSalesMali());
    }
}

export const adamKhesaratSalesMaliRoute: Routes = [
    {
        path: '',
        component: AdamKhesaratSalesMaliComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AdamKhesaratSalesMaliDetailComponent,
        resolve: {
            adamKhesaratSalesMali: AdamKhesaratSalesMaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AdamKhesaratSalesMaliUpdateComponent,
        resolve: {
            adamKhesaratSalesMali: AdamKhesaratSalesMaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AdamKhesaratSalesMaliUpdateComponent,
        resolve: {
            adamKhesaratSalesMali: AdamKhesaratSalesMaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adamKhesaratSalesMaliPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AdamKhesaratSalesMaliDeletePopupComponent,
        resolve: {
            adamKhesaratSalesMali: AdamKhesaratSalesMaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSalesMali.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
