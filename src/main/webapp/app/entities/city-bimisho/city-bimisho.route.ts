import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CityBimisho } from 'app/shared/model/city-bimisho.model';
import { CityBimishoService } from './city-bimisho.service';
import { CityBimishoComponent } from './city-bimisho.component';
import { CityBimishoDetailComponent } from './city-bimisho-detail.component';
import { CityBimishoUpdateComponent } from './city-bimisho-update.component';
import { CityBimishoDeletePopupComponent } from './city-bimisho-delete-dialog.component';
import { ICityBimisho } from 'app/shared/model/city-bimisho.model';

@Injectable({ providedIn: 'root' })
export class CityBimishoResolve implements Resolve<ICityBimisho> {
    constructor(private service: CityBimishoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CityBimisho> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CityBimisho>) => response.ok),
                map((city: HttpResponse<CityBimisho>) => city.body)
            );
        }
        return of(new CityBimisho());
    }
}

export const cityRoute: Routes = [
    {
        path: 'city-bimisho',
        component: CityBimishoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.city.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'city-bimisho/:id/view',
        component: CityBimishoDetailComponent,
        resolve: {
            city: CityBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.city.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'city-bimisho/new',
        component: CityBimishoUpdateComponent,
        resolve: {
            city: CityBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.city.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'city-bimisho/:id/edit',
        component: CityBimishoUpdateComponent,
        resolve: {
            city: CityBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.city.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cityPopupRoute: Routes = [
    {
        path: 'city-bimisho/:id/delete',
        component: CityBimishoDeletePopupComponent,
        resolve: {
            city: CityBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.city.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
