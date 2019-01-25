import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MohasebeBadane } from 'app/shared/model/mohasebe-badane.model';
import { MohasebeBadaneService } from './mohasebe-badane.service';
import { MohasebeBadaneComponent } from './mohasebe-badane.component';
import { MohasebeBadaneDetailComponent } from './mohasebe-badane-detail.component';
import { MohasebeBadaneUpdateComponent } from './mohasebe-badane-update.component';
import { MohasebeBadaneDeletePopupComponent } from './mohasebe-badane-delete-dialog.component';
import { IMohasebeBadane } from 'app/shared/model/mohasebe-badane.model';

@Injectable({ providedIn: 'root' })
export class MohasebeBadaneResolve implements Resolve<IMohasebeBadane> {
    constructor(private service: MohasebeBadaneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MohasebeBadane> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MohasebeBadane>) => response.ok),
                map((mohasebeBadane: HttpResponse<MohasebeBadane>) => mohasebeBadane.body)
            );
        }
        return of(new MohasebeBadane());
    }
}

export const mohasebeBadaneRoute: Routes = [
    {
        path: 'mohasebe-badane',
        component: MohasebeBadaneComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.mohasebeBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mohasebe-badane/:id/view',
        component: MohasebeBadaneDetailComponent,
        resolve: {
            mohasebeBadane: MohasebeBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.mohasebeBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mohasebe-badane/new',
        component: MohasebeBadaneUpdateComponent,
        resolve: {
            mohasebeBadane: MohasebeBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.mohasebeBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mohasebe-badane/:id/edit',
        component: MohasebeBadaneUpdateComponent,
        resolve: {
            mohasebeBadane: MohasebeBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.mohasebeBadane.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mohasebeBadanePopupRoute: Routes = [
    {
        path: 'mohasebe-badane/:id/delete',
        component: MohasebeBadaneDeletePopupComponent,
        resolve: {
            mohasebeBadane: MohasebeBadaneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.mohasebeBadane.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
