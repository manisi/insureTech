import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SalesJaniCalc } from 'app/shared/model/sales-jani-calc.model';
import { SalesJaniCalcService } from './sales-jani-calc.service';
import { SalesJaniCalcComponent } from './sales-jani-calc.component';
import { SalesJaniCalcDetailComponent } from './sales-jani-calc-detail.component';
import { SalesJaniCalcUpdateComponent } from './sales-jani-calc-update.component';
import { SalesJaniCalcDeletePopupComponent } from './sales-jani-calc-delete-dialog.component';
import { ISalesJaniCalc } from 'app/shared/model/sales-jani-calc.model';

@Injectable({ providedIn: 'root' })
export class SalesJaniCalcResolve implements Resolve<ISalesJaniCalc> {
    constructor(private service: SalesJaniCalcService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISalesJaniCalc> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SalesJaniCalc>) => response.ok),
                map((salesJaniCalc: HttpResponse<SalesJaniCalc>) => salesJaniCalc.body)
            );
        }
        return of(new SalesJaniCalc());
    }
}

export const salesJaniCalcRoute: Routes = [
    {
        path: '',
        component: SalesJaniCalcComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesJaniCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SalesJaniCalcDetailComponent,
        resolve: {
            salesJaniCalc: SalesJaniCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesJaniCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SalesJaniCalcUpdateComponent,
        resolve: {
            salesJaniCalc: SalesJaniCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesJaniCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SalesJaniCalcUpdateComponent,
        resolve: {
            salesJaniCalc: SalesJaniCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesJaniCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const salesJaniCalcPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SalesJaniCalcDeletePopupComponent,
        resolve: {
            salesJaniCalc: SalesJaniCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesJaniCalc.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
