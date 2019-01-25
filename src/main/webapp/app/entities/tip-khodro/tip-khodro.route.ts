import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipKhodro } from 'app/shared/model/tip-khodro.model';
import { TipKhodroService } from './tip-khodro.service';
import { TipKhodroComponent } from './tip-khodro.component';
import { TipKhodroDetailComponent } from './tip-khodro-detail.component';
import { TipKhodroUpdateComponent } from './tip-khodro-update.component';
import { TipKhodroDeletePopupComponent } from './tip-khodro-delete-dialog.component';
import { ITipKhodro } from 'app/shared/model/tip-khodro.model';

@Injectable({ providedIn: 'root' })
export class TipKhodroResolve implements Resolve<ITipKhodro> {
    constructor(private service: TipKhodroService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITipKhodro> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipKhodro>) => response.ok),
                map((tipKhodro: HttpResponse<TipKhodro>) => tipKhodro.body)
            );
        }
        return of(new TipKhodro());
    }
}

export const tipKhodroRoute: Routes = [
    {
        path: '',
        component: TipKhodroComponent,
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
        path: ':id/view',
        component: TipKhodroDetailComponent,
        resolve: {
            tipKhodro: TipKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.tipKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TipKhodroUpdateComponent,
        resolve: {
            tipKhodro: TipKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.tipKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TipKhodroUpdateComponent,
        resolve: {
            tipKhodro: TipKhodroResolve
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
        path: ':id/delete',
        component: TipKhodroDeletePopupComponent,
        resolve: {
            tipKhodro: TipKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.tipKhodro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
