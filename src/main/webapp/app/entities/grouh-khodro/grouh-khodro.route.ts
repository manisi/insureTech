import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from './grouh-khodro.service';
import { GrouhKhodroComponent } from './grouh-khodro.component';
import { GrouhKhodroDetailComponent } from './grouh-khodro-detail.component';
import { GrouhKhodroUpdateComponent } from './grouh-khodro-update.component';
import { GrouhKhodroDeletePopupComponent } from './grouh-khodro-delete-dialog.component';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';

@Injectable({ providedIn: 'root' })
export class GrouhKhodroResolve implements Resolve<IGrouhKhodro> {
    constructor(private service: GrouhKhodroService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGrouhKhodro> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<GrouhKhodro>) => response.ok),
                map((grouhKhodro: HttpResponse<GrouhKhodro>) => grouhKhodro.body)
            );
        }
        return of(new GrouhKhodro());
    }
}

export const grouhKhodroRoute: Routes = [
    {
        path: '',
        component: GrouhKhodroComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.grouhKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: GrouhKhodroDetailComponent,
        resolve: {
            grouhKhodro: GrouhKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.grouhKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: GrouhKhodroUpdateComponent,
        resolve: {
            grouhKhodro: GrouhKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.grouhKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: GrouhKhodroUpdateComponent,
        resolve: {
            grouhKhodro: GrouhKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.grouhKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const grouhKhodroPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: GrouhKhodroDeletePopupComponent,
        resolve: {
            grouhKhodro: GrouhKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.grouhKhodro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
