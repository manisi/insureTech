import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Pooshesh } from 'app/shared/model/pooshesh.model';
import { PoosheshService } from './pooshesh.service';
import { PoosheshComponent } from './pooshesh.component';
import { PoosheshDetailComponent } from './pooshesh-detail.component';
import { PoosheshUpdateComponent } from './pooshesh-update.component';
import { PoosheshDeletePopupComponent } from './pooshesh-delete-dialog.component';
import { IPooshesh } from 'app/shared/model/pooshesh.model';

@Injectable({ providedIn: 'root' })
export class PoosheshResolve implements Resolve<IPooshesh> {
    constructor(private service: PoosheshService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Pooshesh> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Pooshesh>) => response.ok),
                map((pooshesh: HttpResponse<Pooshesh>) => pooshesh.body)
            );
        }
        return of(new Pooshesh());
    }
}

export const poosheshRoute: Routes = [
    {
        path: 'pooshesh',
        component: PoosheshComponent,
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
        path: 'pooshesh/:id/view',
        component: PoosheshDetailComponent,
        resolve: {
            pooshesh: PoosheshResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.pooshesh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pooshesh/new',
        component: PoosheshUpdateComponent,
        resolve: {
            pooshesh: PoosheshResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.pooshesh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pooshesh/:id/edit',
        component: PoosheshUpdateComponent,
        resolve: {
            pooshesh: PoosheshResolve
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
        path: 'pooshesh/:id/delete',
        component: PoosheshDeletePopupComponent,
        resolve: {
            pooshesh: PoosheshResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.pooshesh.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
