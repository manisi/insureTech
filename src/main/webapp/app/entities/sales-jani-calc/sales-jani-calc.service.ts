import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'jalali-moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISalesJaniCalc } from 'app/shared/model/sales-jani-calc.model';

type EntityResponseType = HttpResponse<ISalesJaniCalc>;
type EntityArrayResponseType = HttpResponse<ISalesJaniCalc[]>;

@Injectable({ providedIn: 'root' })
export class SalesJaniCalcService {
    public resourceUrl = SERVER_API_URL + 'api/sales-jani-calcs';

    constructor(protected http: HttpClient) {}

    create(salesJaniCalc: ISalesJaniCalc): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(salesJaniCalc);
        return this.http
            .post<ISalesJaniCalc>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(salesJaniCalc: ISalesJaniCalc): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(salesJaniCalc);
        return this.http
            .put<ISalesJaniCalc>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISalesJaniCalc>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISalesJaniCalc[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(salesJaniCalc: ISalesJaniCalc): ISalesJaniCalc {
        const copy: ISalesJaniCalc = Object.assign({}, salesJaniCalc, {
            tarikhShoroFaaliat:
                salesJaniCalc.tarikhShoroFaaliat != null && salesJaniCalc.tarikhShoroFaaliat.isValid()
                    ? salesJaniCalc.tarikhShoroFaaliat.locale('en').utc(true)
                    : null,
            tarikhPayanFaaliat:
                salesJaniCalc.tarikhPayanFaaliat != null && salesJaniCalc.tarikhPayanFaaliat.isValid()
                    ? salesJaniCalc.tarikhPayanFaaliat.locale('en').utc(true)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.tarikhShoroFaaliat = res.body.tarikhShoroFaaliat != null ? moment(res.body.tarikhShoroFaaliat).locale('fa') : null;
            res.body.tarikhPayanFaaliat = res.body.tarikhPayanFaaliat != null ? moment(res.body.tarikhPayanFaaliat).locale('fa') : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((salesJaniCalc: ISalesJaniCalc) => {
                salesJaniCalc.tarikhShoroFaaliat =
                    salesJaniCalc.tarikhShoroFaaliat != null ? moment(salesJaniCalc.tarikhShoroFaaliat).locale('fa') : null;
                salesJaniCalc.tarikhPayanFaaliat =
                    salesJaniCalc.tarikhPayanFaaliat != null ? moment(salesJaniCalc.tarikhPayanFaaliat).locale('fa') : null;
            });
        }
        return res;
    }
}
