import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core/';
import { HomeComponent } from './';
import { HI_THERE_ROUTE } from 'app/hi-there/hi-there.route';
export const HOME_ROUTE: Route = {
    path: '',
    component: HomeComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    },
    canActivate: [UserRouteAccessService],
    children: [HI_THERE_ROUTE]
};
