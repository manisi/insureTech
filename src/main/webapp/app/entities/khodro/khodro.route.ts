import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Khodro } from 'app/shared/model/khodro.model';
import { KhodroService } from './khodro.service';
import { KhodroComponent } from './khodro.component';
import { KhodroDetailComponent } from './khodro-detail.component';
import { KhodroUpdateComponent } from './khodro-update.component';
import { KhodroDeletePopupComponent } from './khodro-delete-dialog.component';
import { IKhodro } from 'app/shared/model/khodro.model';

@Injectable({ providedIn: 'root' })
export class KhodroResolve implements Resolve<IKhodro> {
    constructor(private service: KhodroService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKhodro> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Khodro>) => response.ok),
                map((khodro: HttpResponse<Khodro>) => khodro.body)
            );
        }
        return of(new Khodro());
    }
}

export const khodroRoute: Routes = [
    {
        path: '',
        component: KhodroComponent,
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
        path: ':id/view',
        component: KhodroDetailComponent,
        resolve: {
            khodro: KhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: KhodroUpdateComponent,
        resolve: {
            khodro: KhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: KhodroUpdateComponent,
        resolve: {
            khodro: KhodroResolve
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
        path: ':id/delete',
        component: KhodroDeletePopupComponent,
        resolve: {
            khodro: KhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.khodro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
