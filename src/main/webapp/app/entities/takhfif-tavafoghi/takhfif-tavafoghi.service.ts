import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'jalali-moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';

type EntityResponseType = HttpResponse<ITakhfifTavafoghi>;
type EntityArrayResponseType = HttpResponse<ITakhfifTavafoghi[]>;

@Injectable({ providedIn: 'root' })
export class TakhfifTavafoghiService {
    public resourceUrl = SERVER_API_URL + 'api/takhfif-tavafoghis';

    constructor(protected http: HttpClient) {}

    create(takhfifTavafoghi: ITakhfifTavafoghi): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(takhfifTavafoghi);
        return this.http
            .post<ITakhfifTavafoghi>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(takhfifTavafoghi: ITakhfifTavafoghi): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(takhfifTavafoghi);
        return this.http
            .put<ITakhfifTavafoghi>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITakhfifTavafoghi>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITakhfifTavafoghi[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(takhfifTavafoghi: ITakhfifTavafoghi): ITakhfifTavafoghi {
        const copy: ITakhfifTavafoghi = Object.assign({}, takhfifTavafoghi, {
            azTarikh:
                takhfifTavafoghi.azTarikh != null && takhfifTavafoghi.azTarikh.isValid()
                    ? takhfifTavafoghi.azTarikh.locale('en').utc(true)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.azTarikh = res.body.azTarikh != null ? moment(res.body.azTarikh).locale('fa') : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((takhfifTavafoghi: ITakhfifTavafoghi) => {
                takhfifTavafoghi.azTarikh = takhfifTavafoghi.azTarikh != null ? moment(takhfifTavafoghi.azTarikh).locale('fa') : null;
            });
        }
        return res;
    }
}
