import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';
import { AdamKhesaratSarneshinService } from './adam-khesarat-sarneshin.service';
import { AdamKhesaratSarneshinComponent } from './adam-khesarat-sarneshin.component';
import { AdamKhesaratSarneshinDetailComponent } from './adam-khesarat-sarneshin-detail.component';
import { AdamKhesaratSarneshinUpdateComponent } from './adam-khesarat-sarneshin-update.component';
import { AdamKhesaratSarneshinDeletePopupComponent } from './adam-khesarat-sarneshin-delete-dialog.component';
import { IAdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';

@Injectable({ providedIn: 'root' })
export class AdamKhesaratSarneshinResolve implements Resolve<IAdamKhesaratSarneshin> {
    constructor(private service: AdamKhesaratSarneshinService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAdamKhesaratSarneshin> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AdamKhesaratSarneshin>) => response.ok),
                map((adamKhesaratSarneshin: HttpResponse<AdamKhesaratSarneshin>) => adamKhesaratSarneshin.body)
            );
        }
        return of(new AdamKhesaratSarneshin());
    }
}

export const adamKhesaratSarneshinRoute: Routes = [
    {
        path: '',
        component: AdamKhesaratSarneshinComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSarneshin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AdamKhesaratSarneshinDetailComponent,
        resolve: {
            adamKhesaratSarneshin: AdamKhesaratSarneshinResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSarneshin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AdamKhesaratSarneshinUpdateComponent,
        resolve: {
            adamKhesaratSarneshin: AdamKhesaratSarneshinResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSarneshin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AdamKhesaratSarneshinUpdateComponent,
        resolve: {
            adamKhesaratSarneshin: AdamKhesaratSarneshinResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSarneshin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adamKhesaratSarneshinPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AdamKhesaratSarneshinDeletePopupComponent,
        resolve: {
            adamKhesaratSarneshin: AdamKhesaratSarneshinResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesaratSarneshin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
