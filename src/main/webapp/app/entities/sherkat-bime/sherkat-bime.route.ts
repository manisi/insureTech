import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SherkatBime } from 'app/shared/model/sherkat-bime.model';
import { SherkatBimeService } from './sherkat-bime.service';
import { SherkatBimeComponent } from './sherkat-bime.component';
import { SherkatBimeDetailComponent } from './sherkat-bime-detail.component';
import { SherkatBimeUpdateComponent } from './sherkat-bime-update.component';
import { SherkatBimeDeletePopupComponent } from './sherkat-bime-delete-dialog.component';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';

@Injectable({ providedIn: 'root' })
export class SherkatBimeResolve implements Resolve<ISherkatBime> {
    constructor(private service: SherkatBimeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SherkatBime> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SherkatBime>) => response.ok),
                map((sherkatBime: HttpResponse<SherkatBime>) => sherkatBime.body)
            );
        }
        return of(new SherkatBime());
    }
}

export const sherkatBimeRoute: Routes = [
    {
        path: 'sherkat-bime',
        component: SherkatBimeComponent,
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
        path: 'sherkat-bime/:id/view',
        component: SherkatBimeDetailComponent,
        resolve: {
            sherkatBime: SherkatBimeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sherkatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sherkat-bime/new',
        component: SherkatBimeUpdateComponent,
        resolve: {
            sherkatBime: SherkatBimeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sherkatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sherkat-bime/:id/edit',
        component: SherkatBimeUpdateComponent,
        resolve: {
            sherkatBime: SherkatBimeResolve
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
        path: 'sherkat-bime/:id/delete',
        component: SherkatBimeDeletePopupComponent,
        resolve: {
            sherkatBime: SherkatBimeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.sherkatBime.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
