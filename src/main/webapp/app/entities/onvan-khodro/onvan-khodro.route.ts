import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OnvanKhodro } from 'app/shared/model/onvan-khodro.model';
import { OnvanKhodroService } from './onvan-khodro.service';
import { OnvanKhodroComponent } from './onvan-khodro.component';
import { OnvanKhodroDetailComponent } from './onvan-khodro-detail.component';
import { OnvanKhodroUpdateComponent } from './onvan-khodro-update.component';
import { OnvanKhodroDeletePopupComponent } from './onvan-khodro-delete-dialog.component';
import { IOnvanKhodro } from 'app/shared/model/onvan-khodro.model';

@Injectable({ providedIn: 'root' })
export class OnvanKhodroResolve implements Resolve<IOnvanKhodro> {
    constructor(private service: OnvanKhodroService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOnvanKhodro> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OnvanKhodro>) => response.ok),
                map((onvanKhodro: HttpResponse<OnvanKhodro>) => onvanKhodro.body)
            );
        }
        return of(new OnvanKhodro());
    }
}

export const onvanKhodroRoute: Routes = [
    {
        path: '',
        component: OnvanKhodroComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.onvanKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: OnvanKhodroDetailComponent,
        resolve: {
            onvanKhodro: OnvanKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.onvanKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: OnvanKhodroUpdateComponent,
        resolve: {
            onvanKhodro: OnvanKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.onvanKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: OnvanKhodroUpdateComponent,
        resolve: {
            onvanKhodro: OnvanKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.onvanKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const onvanKhodroPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: OnvanKhodroDeletePopupComponent,
        resolve: {
            onvanKhodro: OnvanKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.onvanKhodro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
