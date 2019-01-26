import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KohnegiBadane } from 'app/shared/model/kohnegi-badane.model';
import { KohnegiBadaneService } from './kohnegi-badane.service';
import { KohnegiBadaneComponent } from './kohnegi-badane.component';
import { KohnegiBadaneDetailComponent } from './kohnegi-badane-detail.component';
import { KohnegiBadaneUpdateComponent } from './kohnegi-badane-update.component';
import { KohnegiBadaneDeletePopupComponent } from './kohnegi-badane-delete-dialog.component';
import { IKohnegiBadane } from 'app/shared/model/kohnegi-badane.model';

@Injectable({ providedIn: 'root' })
export class KohnegiBadaneResolve implements Resolve<IKohnegiBadane> {
    constructor(private service: KohnegiBadaneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKohnegiBadane> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KohnegiBadane>) => response.ok),
                map((kohnegiBadane: HttpResponse<KohnegiBadane>) => kohnegiBadane.body)
            );
        }
        return of(new KohnegiBadane());
    }
}

export const kohnegiBadaneRoute: Routes = [
    {
        path: '',
        component: KohnegiBadaneComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegiBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: KohnegiBadaneDetailComponent,
        resolve: {
            kohnegiBadane: KohnegiBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegiBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: KohnegiBadaneUpdateComponent,
        resolve: {
            kohnegiBadane: KohnegiBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegiBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: KohnegiBadaneUpdateComponent,
        resolve: {
            kohnegiBadane: KohnegiBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegiBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const kohnegiBadanePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: KohnegiBadaneDeletePopupComponent,
        resolve: {
            kohnegiBadane: KohnegiBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegiBadane.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
