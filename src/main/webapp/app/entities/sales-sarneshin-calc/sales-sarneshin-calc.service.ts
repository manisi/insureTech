import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';
// import {JalaliDateValidatorService} from 'ngx-persian';
// import {JalaliDateCalculatorService} from 'ngx-persian';

type EntityResponseType = HttpResponse<ISalesSarneshinCalc>;
type EntityArrayResponseType = HttpResponse<ISalesSarneshinCalc[]>;

@Injectable({ providedIn: 'root' })
export class SalesSarneshinCalcService {
    public resourceUrl = SERVER_API_URL + 'api/sales-sarneshin-calcs';

    constructor(protected http: HttpClient) //   ,private JalaliDateValidatorService: JalaliDateValidatorService,
    // private jalaliDateCalculatorService: JalaliDateCalculatorService
    {}

    create(salesSarneshinCalc: ISalesSarneshinCalc): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(salesSarneshinCalc);
        return this.http
            .post<ISalesSarneshinCalc>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(salesSarneshinCalc: ISalesSarneshinCalc): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(salesSarneshinCalc);
        return this.http
            .put<ISalesSarneshinCalc>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISalesSarneshinCalc>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISalesSarneshinCalc[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(salesSarneshinCalc: ISalesSarneshinCalc): ISalesSarneshinCalc {
        const copy: ISalesSarneshinCalc = Object.assign({}, salesSarneshinCalc, {
            tarikhShoroo:
                salesSarneshinCalc.tarikhShoroo != null && salesSarneshinCalc.tarikhShoroo.isValid()
                    ? salesSarneshinCalc.tarikhShoroo.format(DATE_FORMAT)
                    : null,
            tarikhPayan:
                salesSarneshinCalc.tarikhPayan != null && salesSarneshinCalc.tarikhPayan.isValid()
                    ? salesSarneshinCalc.tarikhPayan.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            // this.jalaliDateCalculatorService.convertToJalali();
            res.body.tarikhShoroo = res.body.tarikhShoroo != null ? moment(res.body.tarikhShoroo) : null;
            //  res.body.tarikhShoroo = res.body.tarikhShoroo != null ? moment(this.jalaliDateCalculatorService.convertToJalali((moment(res.body.tarikhShoroo)).toDate()): null;
            res.body.tarikhPayan = res.body.tarikhPayan != null ? moment(res.body.tarikhPayan) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((salesSarneshinCalc: ISalesSarneshinCalc) => {
                salesSarneshinCalc.tarikhShoroo = salesSarneshinCalc.tarikhShoroo != null ? moment(salesSarneshinCalc.tarikhShoroo) : null;
                salesSarneshinCalc.tarikhPayan = salesSarneshinCalc.tarikhPayan != null ? moment(salesSarneshinCalc.tarikhPayan) : null;
            });
        }
        return res;
    }
}
