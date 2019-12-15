import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';
import { SabegheKhesaratService } from './sabeghe-khesarat.service';
import { SabegheKhesaratComponent } from './sabeghe-khesarat.component';
import { SabegheKhesaratDetailComponent } from './sabeghe-khesarat-detail.component';
import { SabegheKhesaratUpdateComponent } from './sabeghe-khesarat-update.component';
import { SabegheKhesaratDeletePopupComponent } from './sabeghe-khesarat-delete-dialog.component';
import { ISabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';

@Injectable({ providedIn: 'root' })
export class SabegheKhesaratResolve implements Resolve<ISabegheKhesarat> {
    constructor(private service: SabegheKhesaratService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISabegheKhesarat> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SabegheKhesarat>) => response.ok),
                map((sabegheKhesarat: HttpResponse<SabegheKhesarat>) => sabegheKhesarat.body)
            );
        }
        return of(new SabegheKhesarat());
    }
}

export const sabegheKhesaratRoute: Routes = [
    {
        path: '',
        component: SabegheKhesaratComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabegheKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SabegheKhesaratDetailComponent,
        resolve: {
            sabegheKhesarat: SabegheKhesaratResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabegheKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SabegheKhesaratUpdateComponent,
        resolve: {
            sabegheKhesarat: SabegheKhesaratResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabegheKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SabegheKhesaratUpdateComponent,
        resolve: {
            sabegheKhesarat: SabegheKhesaratResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabegheKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sabegheKhesaratPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SabegheKhesaratDeletePopupComponent,
        resolve: {
            sabegheKhesarat: SabegheKhesaratResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sabegheKhesarat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
