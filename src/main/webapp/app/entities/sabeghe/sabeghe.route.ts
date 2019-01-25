import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Sabeghe } from 'app/shared/model/sabeghe.model';
import { SabegheService } from './sabeghe.service';
import { SabegheComponent } from './sabeghe.component';
import { SabegheDetailComponent } from './sabeghe-detail.component';
import { SabegheUpdateComponent } from './sabeghe-update.component';
import { SabegheDeletePopupComponent } from './sabeghe-delete-dialog.component';
import { ISabeghe } from 'app/shared/model/sabeghe.model';

@Injectable({ providedIn: 'root' })
export class SabegheResolve implements Resolve<ISabeghe> {
    constructor(private service: SabegheService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISabeghe> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Sabeghe>) => response.ok),
                map((sabeghe: HttpResponse<Sabeghe>) => sabeghe.body)
            );
        }
        return of(new Sabeghe());
    }
}

export const sabegheRoute: Routes = [
    {
        path: '',
        component: SabegheComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabeghe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SabegheDetailComponent,
        resolve: {
            sabeghe: SabegheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabeghe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SabegheUpdateComponent,
        resolve: {
            sabeghe: SabegheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabeghe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SabegheUpdateComponent,
        resolve: {
            sabeghe: SabegheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabeghe.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sabeghePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SabegheDeletePopupComponent,
        resolve: {
            sabeghe: SabegheResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabeghe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
