import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AnvaeKhodro } from 'app/shared/model/anvae-khodro.model';
import { AnvaeKhodroService } from './anvae-khodro.service';
import { AnvaeKhodroComponent } from './anvae-khodro.component';
import { AnvaeKhodroDetailComponent } from './anvae-khodro-detail.component';
import { AnvaeKhodroUpdateComponent } from './anvae-khodro-update.component';
import { AnvaeKhodroDeletePopupComponent } from './anvae-khodro-delete-dialog.component';
import { IAnvaeKhodro } from 'app/shared/model/anvae-khodro.model';

@Injectable({ providedIn: 'root' })
export class AnvaeKhodroResolve implements Resolve<IAnvaeKhodro> {
    constructor(private service: AnvaeKhodroService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAnvaeKhodro> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AnvaeKhodro>) => response.ok),
                map((anvaeKhodro: HttpResponse<AnvaeKhodro>) => anvaeKhodro.body)
            );
        }
        return of(new AnvaeKhodro());
    }
}

export const anvaeKhodroRoute: Routes = [
    {
        path: '',
        component: AnvaeKhodroComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.anvaeKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AnvaeKhodroDetailComponent,
        resolve: {
            anvaeKhodro: AnvaeKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.anvaeKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AnvaeKhodroUpdateComponent,
        resolve: {
            anvaeKhodro: AnvaeKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.anvaeKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AnvaeKhodroUpdateComponent,
        resolve: {
            anvaeKhodro: AnvaeKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.anvaeKhodro.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const anvaeKhodroPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AnvaeKhodroDeletePopupComponent,
        resolve: {
            anvaeKhodro: AnvaeKhodroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.anvaeKhodro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
