import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISabeghe } from 'app/shared/model/sabeghe.model';

type EntityResponseType = HttpResponse<ISabeghe>;
type EntityArrayResponseType = HttpResponse<ISabeghe[]>;
//moment.locale('fa', { useGregorianParser: true });
@Injectable({ providedIn: 'root' })
export class SabegheService {
    public resourceUrl = SERVER_API_URL + 'api/sabeghes';

    constructor(protected http: HttpClient) {}

    create(sabeghe: ISabeghe): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sabeghe);
        return this.http
            .post<ISabeghe>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sabeghe: ISabeghe): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sabeghe);
        return this.http
            .put<ISabeghe>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISabeghe>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISabeghe[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sabeghe: ISabeghe): ISabeghe {
        const copy: ISabeghe = Object.assign({}, sabeghe, {
            tarikh: sabeghe.tarikh != null && sabeghe.tarikh.isValid() ? sabeghe.tarikh.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.tarikh = res.body.tarikh != null ? moment(res.body.tarikh) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sabeghe: ISabeghe) => {
                sabeghe.tarikh = sabeghe.tarikh != null ? moment(sabeghe.tarikh) : null;
            });
        }
        return res;
    }
}
