import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JarimeDirkard } from 'app/shared/model/jarime-dirkard.model';
import { JarimeDirkardService } from './jarime-dirkard.service';
import { JarimeDirkardComponent } from './jarime-dirkard.component';
import { JarimeDirkardDetailComponent } from './jarime-dirkard-detail.component';
import { JarimeDirkardUpdateComponent } from './jarime-dirkard-update.component';
import { JarimeDirkardDeletePopupComponent } from './jarime-dirkard-delete-dialog.component';
import { IJarimeDirkard } from 'app/shared/model/jarime-dirkard.model';

@Injectable({ providedIn: 'root' })
export class JarimeDirkardResolve implements Resolve<IJarimeDirkard> {
    constructor(private service: JarimeDirkardService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JarimeDirkard> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<JarimeDirkard>) => response.ok),
                map((jarimeDirkard: HttpResponse<JarimeDirkard>) => jarimeDirkard.body)
            );
        }
        return of(new JarimeDirkard());
    }
}

export const jarimeDirkardRoute: Routes = [
    {
        path: 'jarime-dirkard',
        component: JarimeDirkardComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.jarimeDirkard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jarime-dirkard/:id/view',
        component: JarimeDirkardDetailComponent,
        resolve: {
            jarimeDirkard: JarimeDirkardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.jarimeDirkard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jarime-dirkard/new',
        component: JarimeDirkardUpdateComponent,
        resolve: {
            jarimeDirkard: JarimeDirkardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.jarimeDirkard.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jarime-dirkard/:id/edit',
        component: JarimeDirkardUpdateComponent,
        resolve: {
            jarimeDirkard: JarimeDirkardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.jarimeDirkard.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jarimeDirkardPopupRoute: Routes = [
    {
        path: 'jarime-dirkard/:id/delete',
        component: JarimeDirkardDeletePopupComponent,
        resolve: {
            jarimeDirkard: JarimeDirkardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.jarimeDirkard.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
