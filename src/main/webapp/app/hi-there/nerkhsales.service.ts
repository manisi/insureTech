import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { createRequestOption } from 'app/shared';
import { SERVER_API_URL } from 'app/app.constants';
import { SalesNerkhData } from './nerkhSales.model';

@Injectable({ providedIn: 'root' })
export class NerkhSalesService {
    constructor(private http: HttpClient) {}

    query(req: any): Observable<HttpResponse<SalesNerkhData[]>> {
        const params: HttpParams = createRequestOption(req);
        // params.set('fromDate', req.fromDate);
        //params.set('toDate', req.toDate);
        params.set('anvaeKhodro', req.anvaeKhodro);
        params.set('saalSakht', req.saalSakht);
        params.set('vaziatBime', req.vaziatBime);
        params.set('onvanKhodro', req.onvanKhodro);
        params.set('adamKhesarat', req.adamKhesarat);
        params.set('adamKhesaratSarneshin', req.adamKhesaratSarneshin);
        params.set('khesaratSrneshin', req.khesaratSrneshin);
        params.set('khesaratSales', req.khesaratSales);
        params.set('khesaratSalesmali', req.khesaratSalesmali);
        params.set('sherkatBime', req.sherkatBime);
        params.set('tarikhEtebar', req.tarikhEtebar);
        params.set('codeyekta', req.codeyekta);
        params.set('modateBimename', req.modateBimename);
        params.set('sabegheKhesarat', req.sabegheKhesarat);

        // const requestURL = SERVER_API_URL + 'management/audits';
        const requestURL = SERVER_API_URL + 'api/hi-there';

        return this.http.get<SalesNerkhData[]>(requestURL, {
            params,
            observe: 'response'
        });
    }
}
