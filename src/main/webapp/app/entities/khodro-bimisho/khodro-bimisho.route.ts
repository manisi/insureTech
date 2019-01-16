import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KhodroBimisho } from 'app/shared/model/khodro-bimisho.model';
import { KhodroBimishoService } from './khodro-bimisho.service';
import { KhodroBimishoComponent } from './khodro-bimisho.component';
import { KhodroBimishoDetailComponent } from './khodro-bimisho-detail.component';
import { KhodroBimishoUpdateComponent } from './khodro-bimisho-update.component';
import { KhodroBimishoDeletePopupComponent } from './khodro-bimisho-delete-dialog.component';
import { IKhodroBimisho } from 'app/shared/model/khodro-bimisho.model';

@Injectable({ providedIn: 'root' })
export class KhodroBimishoResolve implements Resolve<IKhodroBimisho> {
    constructor(private service: KhodroBimishoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<KhodroBimisho> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KhodroBimisho>) => response.ok),
                map((khodro: HttpResponse<KhodroBimisho>) => khodro.body)
            );
        }
        return of(new KhodroBimisho());
    }
}

export const khodroRoute: Routes = [
    {
        path: 'khodro-bimisho',
        component: KhodroBimishoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.khodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'khodro-bimisho/:id/view',
        component: KhodroBimishoDetailComponent,
        resolve: {
            khodro: KhodroBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'khodro-bimisho/new',
        component: KhodroBimishoUpdateComponent,
        resolve: {
            khodro: KhodroBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'khodro-bimisho/:id/edit',
        component: KhodroBimishoUpdateComponent,
        resolve: {
            khodro: KhodroBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const khodroPopupRoute: Routes = [
    {
        path: 'khodro-bimisho/:id/delete',
        component: KhodroBimishoDeletePopupComponent,
        resolve: {
            khodro: KhodroBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khodro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
