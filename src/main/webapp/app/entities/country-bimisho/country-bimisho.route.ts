import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CountryBimisho } from 'app/shared/model/country-bimisho.model';
import { CountryBimishoService } from './country-bimisho.service';
import { CountryBimishoComponent } from './country-bimisho.component';
import { CountryBimishoDetailComponent } from './country-bimisho-detail.component';
import { CountryBimishoUpdateComponent } from './country-bimisho-update.component';
import { CountryBimishoDeletePopupComponent } from './country-bimisho-delete-dialog.component';
import { ICountryBimisho } from 'app/shared/model/country-bimisho.model';

@Injectable({ providedIn: 'root' })
export class CountryBimishoResolve implements Resolve<ICountryBimisho> {
    constructor(private service: CountryBimishoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CountryBimisho> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CountryBimisho>) => response.ok),
                map((country: HttpResponse<CountryBimisho>) => country.body)
            );
        }
        return of(new CountryBimisho());
    }
}

export const countryRoute: Routes = [
    {
        path: 'country-bimisho',
        component: CountryBimishoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-bimisho/:id/view',
        component: CountryBimishoDetailComponent,
        resolve: {
            country: CountryBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-bimisho/new',
        component: CountryBimishoUpdateComponent,
        resolve: {
            country: CountryBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-bimisho/:id/edit',
        component: CountryBimishoUpdateComponent,
        resolve: {
            country: CountryBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const countryPopupRoute: Routes = [
    {
        path: 'country-bimisho/:id/delete',
        component: CountryBimishoDeletePopupComponent,
        resolve: {
            country: CountryBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.country.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
