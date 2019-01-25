import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAshkhas } from 'app/shared/model/ashkhas.model';

type EntityResponseType = HttpResponse<IAshkhas>;
type EntityArrayResponseType = HttpResponse<IAshkhas[]>;

@Injectable({ providedIn: 'root' })
export class AshkhasService {
    public resourceUrl = SERVER_API_URL + 'api/ashkhas';

    constructor(protected http: HttpClient) {}

    create(ashkhas: IAshkhas): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ashkhas);
        return this.http
            .post<IAshkhas>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(ashkhas: IAshkhas): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ashkhas);
        return this.http
            .put<IAshkhas>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAshkhas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAshkhas[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(ashkhas: IAshkhas): IAshkhas {
        const copy: IAshkhas = Object.assign({}, ashkhas, {
            hireDate: ashkhas.hireDate != null && ashkhas.hireDate.isValid() ? ashkhas.hireDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.hireDate = res.body.hireDate != null ? moment(res.body.hireDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((ashkhas: IAshkhas) => {
                ashkhas.hireDate = ashkhas.hireDate != null ? moment(ashkhas.hireDate) : null;
            });
        }
        return res;
    }
}
