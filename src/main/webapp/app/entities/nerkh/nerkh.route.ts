import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Nerkh } from 'app/shared/model/nerkh.model';
import { NerkhService } from './nerkh.service';
import { NerkhComponent } from './nerkh.component';
import { NerkhDetailComponent } from './nerkh-detail.component';
import { NerkhUpdateComponent } from './nerkh-update.component';
import { NerkhDeletePopupComponent } from './nerkh-delete-dialog.component';
import { INerkh } from 'app/shared/model/nerkh.model';

@Injectable({ providedIn: 'root' })
export class NerkhResolve implements Resolve<INerkh> {
    constructor(private service: NerkhService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<INerkh> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Nerkh>) => response.ok),
                map((nerkh: HttpResponse<Nerkh>) => nerkh.body)
            );
        }
        return of(new Nerkh());
    }
}

export const nerkhRoute: Routes = [
    {
        path: '',
        component: NerkhComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.nerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: NerkhDetailComponent,
        resolve: {
            nerkh: NerkhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.nerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: NerkhUpdateComponent,
        resolve: {
            nerkh: NerkhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.nerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: NerkhUpdateComponent,
        resolve: {
            nerkh: NerkhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.nerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nerkhPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: NerkhDeletePopupComponent,
        resolve: {
            nerkh: NerkhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.nerkh.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
