import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { HiThereComponent } from './hi-there.component';
import { PAGE_ONE_ROUTE } from './page-one/page-one.route';
import { PAGE_TWO_ROUTE } from './page-two/page-two.route';

export const HI_THERE_ROUTE: Route = {
    path: 'hi-there',
    component: HiThereComponent,
    data: {
        authorities: [],
        pageTitle: 'hi-there.title'
    },
    canActivate: [UserRouteAccessService],
    children: [PAGE_ONE_ROUTE, PAGE_TWO_ROUTE]
};
