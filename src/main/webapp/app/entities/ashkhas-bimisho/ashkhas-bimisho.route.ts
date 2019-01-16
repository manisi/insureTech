import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AshkhasBimisho } from 'app/shared/model/ashkhas-bimisho.model';
import { AshkhasBimishoService } from './ashkhas-bimisho.service';
import { AshkhasBimishoComponent } from './ashkhas-bimisho.component';
import { AshkhasBimishoDetailComponent } from './ashkhas-bimisho-detail.component';
import { AshkhasBimishoUpdateComponent } from './ashkhas-bimisho-update.component';
import { AshkhasBimishoDeletePopupComponent } from './ashkhas-bimisho-delete-dialog.component';
import { IAshkhasBimisho } from 'app/shared/model/ashkhas-bimisho.model';

@Injectable({ providedIn: 'root' })
export class AshkhasBimishoResolve implements Resolve<IAshkhasBimisho> {
    constructor(private service: AshkhasBimishoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AshkhasBimisho> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AshkhasBimisho>) => response.ok),
                map((ashkhas: HttpResponse<AshkhasBimisho>) => ashkhas.body)
            );
        }
        return of(new AshkhasBimisho());
    }
}

export const ashkhasRoute: Routes = [
    {
        path: 'ashkhas-bimisho',
        component: AshkhasBimishoComponent,
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
        path: 'ashkhas-bimisho/:id/view',
        component: AshkhasBimishoDetailComponent,
        resolve: {
            ashkhas: AshkhasBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.ashkhas.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ashkhas-bimisho/new',
        component: AshkhasBimishoUpdateComponent,
        resolve: {
            ashkhas: AshkhasBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.ashkhas.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ashkhas-bimisho/:id/edit',
        component: AshkhasBimishoUpdateComponent,
        resolve: {
            ashkhas: AshkhasBimishoResolve
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
        path: 'ashkhas-bimisho/:id/delete',
        component: AshkhasBimishoDeletePopupComponent,
        resolve: {
            ashkhas: AshkhasBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.ashkhas.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
