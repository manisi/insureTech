import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SaalSakht } from 'app/shared/model/saal-sakht.model';
import { SaalSakhtService } from './saal-sakht.service';
import { SaalSakhtComponent } from './saal-sakht.component';
import { SaalSakhtDetailComponent } from './saal-sakht-detail.component';
import { SaalSakhtUpdateComponent } from './saal-sakht-update.component';
import { SaalSakhtDeletePopupComponent } from './saal-sakht-delete-dialog.component';
import { ISaalSakht } from 'app/shared/model/saal-sakht.model';

@Injectable({ providedIn: 'root' })
export class SaalSakhtResolve implements Resolve<ISaalSakht> {
    constructor(private service: SaalSakhtService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISaalSakht> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SaalSakht>) => response.ok),
                map((saalSakht: HttpResponse<SaalSakht>) => saalSakht.body)
            );
        }
        return of(new SaalSakht());
    }
}

export const saalSakhtRoute: Routes = [
    {
        path: '',
        component: SaalSakhtComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.saalSakht.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SaalSakhtDetailComponent,
        resolve: {
            saalSakht: SaalSakhtResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.saalSakht.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SaalSakhtUpdateComponent,
        resolve: {
            saalSakht: SaalSakhtResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.saalSakht.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SaalSakhtUpdateComponent,
        resolve: {
            saalSakht: SaalSakhtResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.saalSakht.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const saalSakhtPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SaalSakhtDeletePopupComponent,
        resolve: {
            saalSakht: SaalSakhtResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.saalSakht.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
