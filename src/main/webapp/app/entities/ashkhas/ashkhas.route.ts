import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Ashkhas } from 'app/shared/model/ashkhas.model';
import { AshkhasService } from './ashkhas.service';
import { AshkhasComponent } from './ashkhas.component';
import { AshkhasDetailComponent } from './ashkhas-detail.component';
import { AshkhasUpdateComponent } from './ashkhas-update.component';
import { AshkhasDeletePopupComponent } from './ashkhas-delete-dialog.component';
import { IAshkhas } from 'app/shared/model/ashkhas.model';

@Injectable({ providedIn: 'root' })
export class AshkhasResolve implements Resolve<IAshkhas> {
    constructor(private service: AshkhasService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAshkhas> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Ashkhas>) => response.ok),
                map((ashkhas: HttpResponse<Ashkhas>) => ashkhas.body)
            );
        }
        return of(new Ashkhas());
    }
}

export const ashkhasRoute: Routes = [
    {
        path: '',
        component: AshkhasComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.ashkhas.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AshkhasDetailComponent,
        resolve: {
            ashkhas: AshkhasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.ashkhas.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AshkhasUpdateComponent,
        resolve: {
            ashkhas: AshkhasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.ashkhas.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AshkhasUpdateComponent,
        resolve: {
            ashkhas: AshkhasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.ashkhas.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ashkhasPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AshkhasDeletePopupComponent,
        resolve: {
            ashkhas: AshkhasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.ashkhas.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
