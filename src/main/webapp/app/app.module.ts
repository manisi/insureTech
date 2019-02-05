import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { Ng2Webstorage } from 'ngx-webstorage';
import { NgJhipsterModule } from 'ng-jhipster';
import { AuthInterceptor } from './blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { InsurancestartSharedModule } from 'app/shared';
import { InsurancestartCoreModule } from 'app/core';
import { InsurancestartAppRoutingModule } from './app-routing.module';
import { InsurancestartHomeModule } from './home/home.module';
import { InsurancestartAccountModule } from './account/account.module';
import { InsurancestartEntityModule } from './entities/entity.module';
import * as moment from 'moment';
import { InsurancestartAppAboutUsModule } from './about-us/about-us.module';
import { InsurancestartAppHiThereModule } from './hi-there/hi-there.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent, NavbarComponent, FooterComponent, PageRibbonComponent, ActiveMenuDirective, ErrorComponent } from './layouts';
import { NgbDateStruct, NgbCalendar, NgbDatepickerI18n, NgbCalendarPersian } from '@ng-bootstrap/ng-bootstrap';
import { NgbDatepickerI18nPersian } from 'app/entities/anvae-khodro/datepicker-jalali';

@NgModule({
    imports: [
        BrowserModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-' }),
        NgJhipsterModule.forRoot({
            // set below to true to make alerts look like toast
            alertAsToast: false,
            alertTimeout: 5000,
            i18nEnabled: true,
            defaultI18nLang: 'fa'
        }),
        InsurancestartSharedModule.forRoot(),
        InsurancestartCoreModule,
        InsurancestartHomeModule,
        InsurancestartAccountModule,
        InsurancestartAppAboutUsModule,
        InsurancestartAppHiThereModule,

        // jhipster-needle-angular-add-module JHipster will add new module here
        InsurancestartEntityModule,
        InsurancestartAppRoutingModule
    ],
    declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true
        },
        {
            provide: NgbCalendar,
            useClass: NgbCalendarPersian
        },
        {
            provide: NgbDatepickerI18n,
            useClass: NgbDatepickerI18nPersian
        }
    ],
    bootstrap: [JhiMainComponent]
})
export class InsurancestartAppModule {
    constructor(private dpConfig: NgbDatepickerConfig) {
        this.dpConfig.minDate = { year: moment().year() - 100, month: 1, day: 1 };
    }
}
