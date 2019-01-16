import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';
import { SherkatBimeBimishoService } from './sherkat-bime-bimisho.service';
import { SherkatBimeBimishoComponent } from './sherkat-bime-bimisho.component';
import { SherkatBimeBimishoDetailComponent } from './sherkat-bime-bimisho-detail.component';
import { SherkatBimeBimishoUpdateComponent } from './sherkat-bime-bimisho-update.component';
import { SherkatBimeBimishoDeletePopupComponent } from './sherkat-bime-bimisho-delete-dialog.component';
import { ISherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';

@Injectable({ providedIn: 'root' })
export class SherkatBimeBimishoResolve implements Resolve<ISherkatBimeBimisho> {
    constructor(private service: SherkatBimeBimishoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SherkatBimeBimisho> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SherkatBimeBimisho>) => response.ok),
                map((sherkatBime: HttpResponse<SherkatBimeBimisho>) => sherkatBime.body)
            );
        }
        return of(new SherkatBimeBimisho());
    }
}

export const sherkatBimeRoute: Routes = [
    {
        path: 'sherkat-bime-bimisho',
        component: SherkatBimeBimishoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'insurancestartApp.sherkatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sherkat-bime-bimisho/:id/view',
        component: SherkatBimeBimishoDetailComponent,
        resolve: {
            sherkatBime: SherkatBimeBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sherkatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sherkat-bime-bimisho/new',
        component: SherkatBimeBimishoUpdateComponent,
        resolve: {
            sherkatBime: SherkatBimeBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sherkatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sherkat-bime-bimisho/:id/edit',
        component: SherkatBimeBimishoUpdateComponent,
        resolve: {
            sherkatBime: SherkatBimeBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sherkatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sherkatBimePopupRoute: Routes = [
    {
        path: 'sherkat-bime-bimisho/:id/delete',
        component: SherkatBimeBimishoDeletePopupComponent,
        resolve: {
            sherkatBime: SherkatBimeBimishoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sherkatBime.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
