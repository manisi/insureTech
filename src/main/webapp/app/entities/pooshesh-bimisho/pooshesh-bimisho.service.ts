import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPoosheshBimisho } from 'app/shared/model/pooshesh-bimisho.model';

type EntityResponseType = HttpResponse<IPoosheshBimisho>;
type EntityArrayResponseType = HttpResponse<IPoosheshBimisho[]>;

@Injectable({ providedIn: 'root' })
export class PoosheshBimishoService {
    public resourceUrl = SERVER_API_URL + 'api/poosheshes';

    constructor(protected http: HttpClient) {}

    create(pooshesh: IPoosheshBimisho): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pooshesh);
        return this.http
            .post<IPoosheshBimisho>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pooshesh: IPoosheshBimisho): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pooshesh);
        return this.http
            .put<IPoosheshBimisho>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPoosheshBimisho>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPoosheshBimisho[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(pooshesh: IPoosheshBimisho): IPoosheshBimisho {
        const copy: IPoosheshBimisho = Object.assign({}, pooshesh, {
            aztarikh: pooshesh.aztarikh != null && pooshesh.aztarikh.isValid() ? pooshesh.aztarikh.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.aztarikh = res.body.aztarikh != null ? moment(res.body.aztarikh) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((pooshesh: IPoosheshBimisho) => {
                pooshesh.aztarikh = pooshesh.aztarikh != null ? moment(pooshesh.aztarikh) : null;
            });
        }
        return res;
    }
}
