import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'jalali-moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOnvanKhodro } from 'app/shared/model/onvan-khodro.model';

type EntityResponseType = HttpResponse<IOnvanKhodro>;
type EntityArrayResponseType = HttpResponse<IOnvanKhodro[]>;

@Injectable({ providedIn: 'root' })
export class OnvanKhodroService {
    public resourceUrl = SERVER_API_URL + 'api/onvan-khodros';

    constructor(protected http: HttpClient) {}

    create(onvanKhodro: IOnvanKhodro): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(onvanKhodro);
        return this.http
            .post<IOnvanKhodro>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(onvanKhodro: IOnvanKhodro): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(onvanKhodro);
        return this.http
            .put<IOnvanKhodro>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IOnvanKhodro>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IOnvanKhodro[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(onvanKhodro: IOnvanKhodro): IOnvanKhodro {
        const copy: IOnvanKhodro = Object.assign({}, onvanKhodro, {
            azTarikh: onvanKhodro.azTarikh != null && onvanKhodro.azTarikh.isValid() ? onvanKhodro.azTarikh.locale('en').utc(true) : null,
            taTarikh: onvanKhodro.taTarikh != null && onvanKhodro.taTarikh.isValid() ? onvanKhodro.taTarikh.locale('en').utc(true) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.azTarikh = res.body.azTarikh != null ? moment(res.body.azTarikh).locale('fa') : null;
            res.body.taTarikh = res.body.taTarikh != null ? moment(res.body.taTarikh).locale('fa') : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((onvanKhodro: IOnvanKhodro) => {
                onvanKhodro.azTarikh = onvanKhodro.azTarikh != null ? moment(onvanKhodro.azTarikh).locale('fa') : null;
                onvanKhodro.taTarikh = onvanKhodro.taTarikh != null ? moment(onvanKhodro.taTarikh).locale('fa') : null;
            });
        }
        return res;
    }
}
