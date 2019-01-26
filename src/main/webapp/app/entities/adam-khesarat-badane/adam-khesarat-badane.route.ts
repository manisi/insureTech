import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';
import { AdamKhesaratBadaneService } from './adam-khesarat-badane.service';
import { AdamKhesaratBadaneComponent } from './adam-khesarat-badane.component';
import { AdamKhesaratBadaneDetailComponent } from './adam-khesarat-badane-detail.component';
import { AdamKhesaratBadaneUpdateComponent } from './adam-khesarat-badane-update.component';
import { AdamKhesaratBadaneDeletePopupComponent } from './adam-khesarat-badane-delete-dialog.component';
import { IAdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';

@Injectable({ providedIn: 'root' })
export class AdamKhesaratBadaneResolve implements Resolve<IAdamKhesaratBadane> {
    constructor(private service: AdamKhesaratBadaneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAdamKhesaratBadane> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AdamKhesaratBadane>) => response.ok),
                map((adamKhesaratBadane: HttpResponse<AdamKhesaratBadane>) => adamKhesaratBadane.body)
            );
        }
        return of(new AdamKhesaratBadane());
    }
}

export const adamKhesaratBadaneRoute: Routes = [
    {
        path: '',
        component: AdamKhesaratBadaneComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AdamKhesaratBadaneDetailComponent,
        resolve: {
            adamKhesaratBadane: AdamKhesaratBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AdamKhesaratBadaneUpdateComponent,
        resolve: {
            adamKhesaratBadane: AdamKhesaratBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AdamKhesaratBadaneUpdateComponent,
        resolve: {
            adamKhesaratBadane: AdamKhesaratBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adamKhesaratBadanePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AdamKhesaratBadaneDeletePopupComponent,
        resolve: {
            adamKhesaratBadane: AdamKhesaratBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratBadane.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
