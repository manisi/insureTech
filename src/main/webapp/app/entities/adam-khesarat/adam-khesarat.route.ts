import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AdamKhesarat } from 'app/shared/model/adam-khesarat.model';
import { AdamKhesaratService } from './adam-khesarat.service';
import { AdamKhesaratComponent } from './adam-khesarat.component';
import { AdamKhesaratDetailComponent } from './adam-khesarat-detail.component';
import { AdamKhesaratUpdateComponent } from './adam-khesarat-update.component';
import { AdamKhesaratDeletePopupComponent } from './adam-khesarat-delete-dialog.component';
import { IAdamKhesarat } from 'app/shared/model/adam-khesarat.model';

@Injectable({ providedIn: 'root' })
export class AdamKhesaratResolve implements Resolve<IAdamKhesarat> {
    constructor(private service: AdamKhesaratService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAdamKhesarat> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AdamKhesarat>) => response.ok),
                map((adamKhesarat: HttpResponse<AdamKhesarat>) => adamKhesarat.body)
            );
        }
        return of(new AdamKhesarat());
    }
}

export const adamKhesaratRoute: Routes = [
    {
        path: '',
        component: AdamKhesaratComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AdamKhesaratDetailComponent,
        resolve: {
            adamKhesarat: AdamKhesaratResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AdamKhesaratUpdateComponent,
        resolve: {
            adamKhesarat: AdamKhesaratResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AdamKhesaratUpdateComponent,
        resolve: {
            adamKhesarat: AdamKhesaratResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adamKhesaratPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AdamKhesaratDeletePopupComponent,
        resolve: {
            adamKhesarat: AdamKhesaratResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.adamKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
