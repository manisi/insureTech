import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';
import { SalesSarneshinCalcService } from './sales-sarneshin-calc.service';
import { SalesSarneshinCalcComponent } from './sales-sarneshin-calc.component';
import { SalesSarneshinCalcDetailComponent } from './sales-sarneshin-calc-detail.component';
import { SalesSarneshinCalcUpdateComponent } from './sales-sarneshin-calc-update.component';
import { SalesSarneshinCalcDeletePopupComponent } from './sales-sarneshin-calc-delete-dialog.component';
import { ISalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';

@Injectable({ providedIn: 'root' })
export class SalesSarneshinCalcResolve implements Resolve<ISalesSarneshinCalc> {
    constructor(private service: SalesSarneshinCalcService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISalesSarneshinCalc> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SalesSarneshinCalc>) => response.ok),
                map((salesSarneshinCalc: HttpResponse<SalesSarneshinCalc>) => salesSarneshinCalc.body)
            );
        }
        return of(new SalesSarneshinCalc());
    }
}

export const salesSarneshinCalcRoute: Routes = [
    {
        path: '',
        component: SalesSarneshinCalcComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesSarneshinCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SalesSarneshinCalcDetailComponent,
        resolve: {
            salesSarneshinCalc: SalesSarneshinCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesSarneshinCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SalesSarneshinCalcUpdateComponent,
        resolve: {
            salesSarneshinCalc: SalesSarneshinCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesSarneshinCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SalesSarneshinCalcUpdateComponent,
        resolve: {
            salesSarneshinCalc: SalesSarneshinCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesSarneshinCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const salesSarneshinCalcPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SalesSarneshinCalcDeletePopupComponent,
        resolve: {
            salesSarneshinCalc: SalesSarneshinCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesSarneshinCalc.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
