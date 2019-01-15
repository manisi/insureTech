import { Route } from '@angular/router';

import { InsutechConfigurationComponent } from './configuration.component';

export const configurationRoute: Route = {
    path: 'insutech-configuration',
    component: InsutechConfigurationComponent,
    data: {
        pageTitle: 'configuration.title'
    }
};
