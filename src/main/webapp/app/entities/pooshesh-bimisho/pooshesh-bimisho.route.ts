import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PoosheshBimisho } from 'app/shared/model/pooshesh-bimisho.model';
import { PoosheshBimishoService } from './pooshesh-bimisho.service';
import { PoosheshBimishoComponent } from './pooshesh-bimisho.component';
import { PoosheshBimishoDetailComponent } from './pooshesh-bimisho-detail.component';
import { PoosheshBimishoUpdateComponent } from './pooshesh-bimisho-update.component';
import { PoosheshBimishoDeletePopupComponent } from './pooshesh-bimisho-delete-dialog.component';
import { IPoosheshBimisho } from 'app/shared/model/pooshesh-bimisho.model';

@Injectable({ providedIn: 'root' })
export class PoosheshBimishoResolve implements Resolve<IPoosheshBimisho> {
    constructor(private service: PoosheshBimishoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PoosheshBimisho> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PoosheshBimisho>) => response.ok),
                map((pooshesh: HttpResponse<PoosheshBimisho>) => pooshesh.body)
            );
        }
        return of(new PoosheshBimisho());
    }
}

export const poosheshRoute: Routes = [
    {
        path: 'pooshesh-bimisho',
        component: PoosheshBimishoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.pooshesh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pooshesh-bimisho/:id/view',
        component: PoosheshBimishoDetailComponent,
        resolve: {
            pooshesh: PoosheshBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.pooshesh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pooshesh-bimisho/new',
        component: PoosheshBimishoUpdateComponent,
        resolve: {
            pooshesh: PoosheshBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.pooshesh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pooshesh-bimisho/:id/edit',
        component: PoosheshBimishoUpdateComponent,
        resolve: {
            pooshesh: PoosheshBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.pooshesh.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const poosheshPopupRoute: Routes = [
    {
        path: 'pooshesh-bimisho/:id/delete',
        component: PoosheshBimishoDeletePopupComponent,
        resolve: {
            pooshesh: PoosheshBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.pooshesh.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
