import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VaziatBime } from 'app/shared/model/vaziat-bime.model';
import { VaziatBimeService } from './vaziat-bime.service';
import { VaziatBimeComponent } from './vaziat-bime.component';
import { VaziatBimeDetailComponent } from './vaziat-bime-detail.component';
import { VaziatBimeUpdateComponent } from './vaziat-bime-update.component';
import { VaziatBimeDeletePopupComponent } from './vaziat-bime-delete-dialog.component';
import { IVaziatBime } from 'app/shared/model/vaziat-bime.model';

@Injectable({ providedIn: 'root' })
export class VaziatBimeResolve implements Resolve<IVaziatBime> {
    constructor(private service: VaziatBimeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVaziatBime> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<VaziatBime>) => response.ok),
                map((vaziatBime: HttpResponse<VaziatBime>) => vaziatBime.body)
            );
        }
        return of(new VaziatBime());
    }
}

export const vaziatBimeRoute: Routes = [
    {
        path: '',
        component: VaziatBimeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.vaziatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: VaziatBimeDetailComponent,
        resolve: {
            vaziatBime: VaziatBimeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.vaziatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: VaziatBimeUpdateComponent,
        resolve: {
            vaziatBime: VaziatBimeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.vaziatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: VaziatBimeUpdateComponent,
        resolve: {
            vaziatBime: VaziatBimeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.vaziatBime.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vaziatBimePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: VaziatBimeDeletePopupComponent,
        resolve: {
            vaziatBime: VaziatBimeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.vaziatBime.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
