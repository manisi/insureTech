import { Route } from '@angular/router';

import { InsutechDocsComponent } from './docs.component';

export const docsRoute: Route = {
    path: 'docs',
    component: InsutechDocsComponent,
    data: {
        pageTitle: 'global.menu.admin.apidocs'
    }
};
