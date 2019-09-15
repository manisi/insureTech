import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GroupOperationsData } from 'app/shared/model/group-operations-data.model';
import { GroupOperationsDataService } from './group-operations-data.service';
import { GroupOperationsDataComponent } from './group-operations-data.component';
import { GroupOperationsDataDetailComponent } from './group-operations-data-detail.component';
import { GroupOperationsDataUpdateComponent } from './group-operations-data-update.component';
import { GroupOperationsDataDeletePopupComponent } from './group-operations-data-delete-dialog.component';
import { GroupOperationsDataApplyFilePopupComponent } from './group-operations-data-applyfile-dialog.component';
import { IGroupOperationsData } from 'app/shared/model/group-operations-data.model';

@Injectable({ providedIn: 'root' })
export class GroupOperationsDataResolve implements Resolve<IGroupOperationsData> {
    constructor(private service: GroupOperationsDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGroupOperationsData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<GroupOperationsData>) => response.ok),
                map((groupOperationsData: HttpResponse<GroupOperationsData>) => groupOperationsData.body)
            );
        }
        return of(new GroupOperationsData());
    }
}

export const groupOperationsDataRoute: Routes = [
    {
        path: '',
        component: GroupOperationsDataComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.groupOperationsData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: GroupOperationsDataDetailComponent,
        resolve: {
            groupOperationsData: GroupOperationsDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.groupOperationsData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: GroupOperationsDataUpdateComponent,
        resolve: {
            groupOperationsData: GroupOperationsDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.groupOperationsData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: GroupOperationsDataUpdateComponent,
        resolve: {
            groupOperationsData: GroupOperationsDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.groupOperationsData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const groupOperationsDataPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: GroupOperationsDataDeletePopupComponent,
        resolve: {
            groupOperationsData: GroupOperationsDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.groupOperationsData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: ':id/applyfile',
        component: GroupOperationsDataApplyFilePopupComponent,
        resolve: {
            groupOperationsData: GroupOperationsDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'insurancestartApp.groupOperationsData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
