import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMoredEstefadeSales } from 'app/shared/model/mored-estefade-sales.model';

type EntityResponseType = HttpResponse<IMoredEstefadeSales>;
type EntityArrayResponseType = HttpResponse<IMoredEstefadeSales[]>;

@Injectable({ providedIn: 'root' })
export class MoredEstefadeSalesService {
    public resourceUrl = SERVER_API_URL + 'api/mored-estefade-sales';

    constructor(protected http: HttpClient) {}

    create(moredEstefadeSales: IMoredEstefadeSales): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(moredEstefadeSales);
        return this.http
            .post<IMoredEstefadeSales>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(moredEstefadeSales: IMoredEstefadeSales): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(moredEstefadeSales);
        return this.http
            .put<IMoredEstefadeSales>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMoredEstefadeSales>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMoredEstefadeSales[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(moredEstefadeSales: IMoredEstefadeSales): IMoredEstefadeSales {
        const copy: IMoredEstefadeSales = Object.assign({}, moredEstefadeSales, {
            azTarikh:
                moredEstefadeSales.azTarikh != null && moredEstefadeSales.azTarikh.isValid()
                    ? moredEstefadeSales.azTarikh.format(DATE_FORMAT)
                    : null,
            taTarikh:
                moredEstefadeSales.taTarikh != null && moredEstefadeSales.taTarikh.isValid()
                    ? moredEstefadeSales.taTarikh.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.azTarikh = res.body.azTarikh != null ? moment(res.body.azTarikh) : null;
            res.body.taTarikh = res.body.taTarikh != null ? moment(res.body.taTarikh) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((moredEstefadeSales: IMoredEstefadeSales) => {
                moredEstefadeSales.azTarikh = moredEstefadeSales.azTarikh != null ? moment(moredEstefadeSales.azTarikh) : null;
                moredEstefadeSales.taTarikh = moredEstefadeSales.taTarikh != null ? moment(moredEstefadeSales.taTarikh) : null;
            });
        }
        return res;
    }
}
