import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';
import { NerkhBimishoService } from './nerkh-bimisho.service';
import { NerkhBimishoComponent } from './nerkh-bimisho.component';
import { NerkhBimishoDetailComponent } from './nerkh-bimisho-detail.component';
import { NerkhBimishoUpdateComponent } from './nerkh-bimisho-update.component';
import { NerkhBimishoDeletePopupComponent } from './nerkh-bimisho-delete-dialog.component';
import { INerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';

@Injectable({ providedIn: 'root' })
export class NerkhBimishoResolve implements Resolve<INerkhBimisho> {
    constructor(private service: NerkhBimishoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NerkhBimisho> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NerkhBimisho>) => response.ok),
                map((nerkh: HttpResponse<NerkhBimisho>) => nerkh.body)
            );
        }
        return of(new NerkhBimisho());
    }
}

export const nerkhRoute: Routes = [
    {
        path: 'nerkh-bimisho',
        component: NerkhBimishoComponent,
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
        path: 'nerkh-bimisho/:id/view',
        component: NerkhBimishoDetailComponent,
        resolve: {
            nerkh: NerkhBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.nerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nerkh-bimisho/new',
        component: NerkhBimishoUpdateComponent,
        resolve: {
            nerkh: NerkhBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.nerkh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nerkh-bimisho/:id/edit',
        component: NerkhBimishoUpdateComponent,
        resolve: {
            nerkh: NerkhBimishoResolve
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
        path: 'nerkh-bimisho/:id/delete',
        component: NerkhBimishoDeletePopupComponent,
        resolve: {
            nerkh: NerkhBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.nerkh.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
