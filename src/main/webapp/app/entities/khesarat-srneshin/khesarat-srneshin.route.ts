import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KhesaratSrneshin } from 'app/shared/model/khesarat-srneshin.model';
import { KhesaratSrneshinService } from './khesarat-srneshin.service';
import { KhesaratSrneshinComponent } from './khesarat-srneshin.component';
import { KhesaratSrneshinDetailComponent } from './khesarat-srneshin-detail.component';
import { KhesaratSrneshinUpdateComponent } from './khesarat-srneshin-update.component';
import { KhesaratSrneshinDeletePopupComponent } from './khesarat-srneshin-delete-dialog.component';
import { IKhesaratSrneshin } from 'app/shared/model/khesarat-srneshin.model';

@Injectable({ providedIn: 'root' })
export class KhesaratSrneshinResolve implements Resolve<IKhesaratSrneshin> {
    constructor(private service: KhesaratSrneshinService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKhesaratSrneshin> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KhesaratSrneshin>) => response.ok),
                map((khesaratSrneshin: HttpResponse<KhesaratSrneshin>) => khesaratSrneshin.body)
            );
        }
        return of(new KhesaratSrneshin());
    }
}

export const khesaratSrneshinRoute: Routes = [
    {
        path: '',
        component: KhesaratSrneshinComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSrneshin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: KhesaratSrneshinDetailComponent,
        resolve: {
            khesaratSrneshin: KhesaratSrneshinResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSrneshin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: KhesaratSrneshinUpdateComponent,
        resolve: {
            khesaratSrneshin: KhesaratSrneshinResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSrneshin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: KhesaratSrneshinUpdateComponent,
        resolve: {
            khesaratSrneshin: KhesaratSrneshinResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSrneshin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const khesaratSrneshinPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: KhesaratSrneshinDeletePopupComponent,
        resolve: {
            khesaratSrneshin: KhesaratSrneshinResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khesaratSrneshin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
