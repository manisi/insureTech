import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'jalali-moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISalesMazadCalc } from 'app/shared/model/sales-mazad-calc.model';

type EntityResponseType = HttpResponse<ISalesMazadCalc>;
type EntityArrayResponseType = HttpResponse<ISalesMazadCalc[]>;

@Injectable({ providedIn: 'root' })
export class SalesMazadCalcService {
    public resourceUrl = SERVER_API_URL + 'api/sales-mazad-calcs';

    constructor(protected http: HttpClient) {}

    create(salesMazadCalc: ISalesMazadCalc): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(salesMazadCalc);
        return this.http
            .post<ISalesMazadCalc>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(salesMazadCalc: ISalesMazadCalc): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(salesMazadCalc);
        return this.http
            .put<ISalesMazadCalc>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISalesMazadCalc>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISalesMazadCalc[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(salesMazadCalc: ISalesMazadCalc): ISalesMazadCalc {
        const copy: ISalesMazadCalc = Object.assign({}, salesMazadCalc, {
            tarikhShoroo:
                salesMazadCalc.tarikhShoroo != null && salesMazadCalc.tarikhShoroo.isValid()
                    ? salesMazadCalc.tarikhShoroo.locale('en').utc(true)
                    : null,
            tarikhPayan:
                salesMazadCalc.tarikhPayan != null && salesMazadCalc.tarikhPayan.isValid()
                    ? salesMazadCalc.tarikhPayan.locale('en').utc(true)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.tarikhShoroo = res.body.tarikhShoroo != null ? moment(res.body.tarikhShoroo).locale('fa') : null;
            res.body.tarikhPayan = res.body.tarikhPayan != null ? moment(res.body.tarikhPayan).locale('fa') : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((salesMazadCalc: ISalesMazadCalc) => {
                salesMazadCalc.tarikhShoroo = salesMazadCalc.tarikhShoroo != null ? moment(salesMazadCalc.tarikhShoroo).locale('fa') : null;
                salesMazadCalc.tarikhPayan = salesMazadCalc.tarikhPayan != null ? moment(salesMazadCalc.tarikhPayan).locale('fa') : null;
            });
        }
        return res;
    }
}
