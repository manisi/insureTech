import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ModateBimename } from 'app/shared/model/modate-bimename.model';
import { ModateBimenameService } from './modate-bimename.service';
import { ModateBimenameComponent } from './modate-bimename.component';
import { ModateBimenameDetailComponent } from './modate-bimename-detail.component';
import { ModateBimenameUpdateComponent } from './modate-bimename-update.component';
import { ModateBimenameDeletePopupComponent } from './modate-bimename-delete-dialog.component';
import { IModateBimename } from 'app/shared/model/modate-bimename.model';

@Injectable({ providedIn: 'root' })
export class ModateBimenameResolve implements Resolve<IModateBimename> {
    constructor(private service: ModateBimenameService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IModateBimename> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ModateBimename>) => response.ok),
                map((modateBimename: HttpResponse<ModateBimename>) => modateBimename.body)
            );
        }
        return of(new ModateBimename());
    }
}

export const modateBimenameRoute: Routes = [
    {
        path: '',
        component: ModateBimenameComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.modateBimename.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ModateBimenameDetailComponent,
        resolve: {
            modateBimename: ModateBimenameResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.modateBimename.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ModateBimenameUpdateComponent,
        resolve: {
            modateBimename: ModateBimenameResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.modateBimename.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ModateBimenameUpdateComponent,
        resolve: {
            modateBimename: ModateBimenameResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.modateBimename.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const modateBimenamePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ModateBimenameDeletePopupComponent,
        resolve: {
            modateBimename: ModateBimenameResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.modateBimename.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
