import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';
import { TakhfifTavafoghiService } from './takhfif-tavafoghi.service';
import { TakhfifTavafoghiComponent } from './takhfif-tavafoghi.component';
import { TakhfifTavafoghiDetailComponent } from './takhfif-tavafoghi-detail.component';
import { TakhfifTavafoghiUpdateComponent } from './takhfif-tavafoghi-update.component';
import { TakhfifTavafoghiDeletePopupComponent } from './takhfif-tavafoghi-delete-dialog.component';
import { ITakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';

@Injectable({ providedIn: 'root' })
export class TakhfifTavafoghiResolve implements Resolve<ITakhfifTavafoghi> {
    constructor(private service: TakhfifTavafoghiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITakhfifTavafoghi> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TakhfifTavafoghi>) => response.ok),
                map((takhfifTavafoghi: HttpResponse<TakhfifTavafoghi>) => takhfifTavafoghi.body)
            );
        }
        return of(new TakhfifTavafoghi());
    }
}

export const takhfifTavafoghiRoute: Routes = [
    {
        path: '',
        component: TakhfifTavafoghiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.takhfifTavafoghi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TakhfifTavafoghiDetailComponent,
        resolve: {
            takhfifTavafoghi: TakhfifTavafoghiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.takhfifTavafoghi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TakhfifTavafoghiUpdateComponent,
        resolve: {
            takhfifTavafoghi: TakhfifTavafoghiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.takhfifTavafoghi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TakhfifTavafoghiUpdateComponent,
        resolve: {
            takhfifTavafoghi: TakhfifTavafoghiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.takhfifTavafoghi.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const takhfifTavafoghiPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TakhfifTavafoghiDeletePopupComponent,
        resolve: {
            takhfifTavafoghi: TakhfifTavafoghiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.takhfifTavafoghi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
