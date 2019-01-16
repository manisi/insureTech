import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipKhodroBimisho } from 'app/shared/model/tip-khodro-bimisho.model';
import { TipKhodroBimishoService } from './tip-khodro-bimisho.service';
import { TipKhodroBimishoComponent } from './tip-khodro-bimisho.component';
import { TipKhodroBimishoDetailComponent } from './tip-khodro-bimisho-detail.component';
import { TipKhodroBimishoUpdateComponent } from './tip-khodro-bimisho-update.component';
import { TipKhodroBimishoDeletePopupComponent } from './tip-khodro-bimisho-delete-dialog.component';
import { ITipKhodroBimisho } from 'app/shared/model/tip-khodro-bimisho.model';

@Injectable({ providedIn: 'root' })
export class TipKhodroBimishoResolve implements Resolve<ITipKhodroBimisho> {
    constructor(private service: TipKhodroBimishoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipKhodroBimisho> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipKhodroBimisho>) => response.ok),
                map((tipKhodro: HttpResponse<TipKhodroBimisho>) => tipKhodro.body)
            );
        }
        return of(new TipKhodroBimisho());
    }
}

export const tipKhodroRoute: Routes = [
    {
        path: 'tip-khodro-bimisho',
        component: TipKhodroBimishoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.tipKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tip-khodro-bimisho/:id/view',
        component: TipKhodroBimishoDetailComponent,
        resolve: {
            tipKhodro: TipKhodroBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.tipKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tip-khodro-bimisho/new',
        component: TipKhodroBimishoUpdateComponent,
        resolve: {
            tipKhodro: TipKhodroBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.tipKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tip-khodro-bimisho/:id/edit',
        component: TipKhodroBimishoUpdateComponent,
        resolve: {
            tipKhodro: TipKhodroBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.tipKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipKhodroPopupRoute: Routes = [
    {
        path: 'tip-khodro-bimisho/:id/delete',
        component: TipKhodroBimishoDeletePopupComponent,
        resolve: {
            tipKhodro: TipKhodroBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.tipKhodro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
