import { Route } from '@angular/router';

import { InsutechHealthCheckComponent } from './health.component';

export const healthRoute: Route = {
    path: 'insutech-health',
    component: InsutechHealthCheckComponent,
    data: {
        pageTitle: 'health.title'
    }
};
