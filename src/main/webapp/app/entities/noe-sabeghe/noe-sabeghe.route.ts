import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NoeSabeghe } from 'app/shared/model/noe-sabeghe.model';
import { NoeSabegheService } from './noe-sabeghe.service';
import { NoeSabegheComponent } from './noe-sabeghe.component';
import { NoeSabegheDetailComponent } from './noe-sabeghe-detail.component';
import { NoeSabegheUpdateComponent } from './noe-sabeghe-update.component';
import { NoeSabegheDeletePopupComponent } from './noe-sabeghe-delete-dialog.component';
import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';

@Injectable({ providedIn: 'root' })
export class NoeSabegheResolve implements Resolve<INoeSabeghe> {
    constructor(private service: NoeSabegheService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<INoeSabeghe> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NoeSabeghe>) => response.ok),
                map((noeSabeghe: HttpResponse<NoeSabeghe>) => noeSabeghe.body)
            );
        }
        return of(new NoeSabeghe());
    }
}

export const noeSabegheRoute: Routes = [
    {
        path: '',
        component: NoeSabegheComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.noeSabeghe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: NoeSabegheDetailComponent,
        resolve: {
            noeSabeghe: NoeSabegheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.noeSabeghe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: NoeSabegheUpdateComponent,
        resolve: {
            noeSabeghe: NoeSabegheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.noeSabeghe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: NoeSabegheUpdateComponent,
        resolve: {
            noeSabeghe: NoeSabegheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.noeSabeghe.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const noeSabeghePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: NoeSabegheDeletePopupComponent,
        resolve: {
            noeSabeghe: NoeSabegheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.noeSabeghe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
