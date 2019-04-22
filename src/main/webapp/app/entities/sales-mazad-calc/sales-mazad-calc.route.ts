import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SalesMazadCalc } from 'app/shared/model/sales-mazad-calc.model';
import { SalesMazadCalcService } from './sales-mazad-calc.service';
import { SalesMazadCalcComponent } from './sales-mazad-calc.component';
import { SalesMazadCalcDetailComponent } from './sales-mazad-calc-detail.component';
import { SalesMazadCalcUpdateComponent } from './sales-mazad-calc-update.component';
import { SalesMazadCalcDeletePopupComponent } from './sales-mazad-calc-delete-dialog.component';
import { ISalesMazadCalc } from 'app/shared/model/sales-mazad-calc.model';

@Injectable({ providedIn: 'root' })
export class SalesMazadCalcResolve implements Resolve<ISalesMazadCalc> {
    constructor(private service: SalesMazadCalcService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISalesMazadCalc> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SalesMazadCalc>) => response.ok),
                map((salesMazadCalc: HttpResponse<SalesMazadCalc>) => salesMazadCalc.body)
            );
        }
        return of(new SalesMazadCalc());
    }
}

export const salesMazadCalcRoute: Routes = [
    {
        path: '',
        component: SalesMazadCalcComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesMazadCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SalesMazadCalcDetailComponent,
        resolve: {
            salesMazadCalc: SalesMazadCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesMazadCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SalesMazadCalcUpdateComponent,
        resolve: {
            salesMazadCalc: SalesMazadCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesMazadCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SalesMazadCalcUpdateComponent,
        resolve: {
            salesMazadCalc: SalesMazadCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesMazadCalc.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const salesMazadCalcPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SalesMazadCalcDeletePopupComponent,
        resolve: {
            salesMazadCalc: SalesMazadCalcResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.salesMazadCalc.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
