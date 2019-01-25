import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Kohnegi } from 'app/shared/model/kohnegi.model';
import { KohnegiService } from './kohnegi.service';
import { KohnegiComponent } from './kohnegi.component';
import { KohnegiDetailComponent } from './kohnegi-detail.component';
import { KohnegiUpdateComponent } from './kohnegi-update.component';
import { KohnegiDeletePopupComponent } from './kohnegi-delete-dialog.component';
import { IKohnegi } from 'app/shared/model/kohnegi.model';

@Injectable({ providedIn: 'root' })
export class KohnegiResolve implements Resolve<IKohnegi> {
    constructor(private service: KohnegiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKohnegi> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Kohnegi>) => response.ok),
                map((kohnegi: HttpResponse<Kohnegi>) => kohnegi.body)
            );
        }
        return of(new Kohnegi());
    }
}

export const kohnegiRoute: Routes = [
    {
        path: '',
        component: KohnegiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: KohnegiDetailComponent,
        resolve: {
            kohnegi: KohnegiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: KohnegiUpdateComponent,
        resolve: {
            kohnegi: KohnegiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: KohnegiUpdateComponent,
        resolve: {
            kohnegi: KohnegiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegi.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const kohnegiPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: KohnegiDeletePopupComponent,
        resolve: {
            kohnegi: KohnegiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.kohnegi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
