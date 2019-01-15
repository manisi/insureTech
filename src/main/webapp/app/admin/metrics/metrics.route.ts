import { Route } from '@angular/router';

import { InsutechMetricsMonitoringComponent } from './metrics.component';

export const metricsRoute: Route = {
    path: 'insutech-metrics',
    component: InsutechMetricsMonitoringComponent,
    data: {
        pageTitle: 'metrics.title'
    }
};
