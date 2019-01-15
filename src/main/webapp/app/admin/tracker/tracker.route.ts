import { Route } from '@angular/router';

import { InsutechTrackerComponent } from './tracker.component';

export const trackerRoute: Route = {
    path: 'insutech-tracker',
    component: InsutechTrackerComponent,
    data: {
        pageTitle: 'tracker.title'
    }
};
