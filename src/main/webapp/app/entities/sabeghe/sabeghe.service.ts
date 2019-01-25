import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { createSearchRequest } from 'app/shared/util/search-util.ts';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISabeghe } from 'app/shared/model/sabeghe.model';

type EntityResponseType = HttpResponse<ISabeghe>;
type EntityArrayResponseType = HttpResponse<ISabeghe[]>;

@Injectable({ providedIn: 'root' })
export class SabegheService {
    public resourceUrl = SERVER_API_URL + 'api/sabeghes';

    constructor(protected http: HttpClient) {}

    create(sabeghe: ISabeghe): Observable<EntityResponseType> {
        return this.http.post<ISabeghe>(this.resourceUrl, sabeghe, { observe: 'response' });
    }

    update(sabeghe: ISabeghe): Observable<EntityResponseType> {
        return this.http.put<ISabeghe>(this.resourceUrl, sabeghe, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISabeghe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISabeghe[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    //search
    search(req: any[], pageable: any): Observable<EntityArrayResponseType> {
        const options = createSearchRequest(req, pageable);
        return this.http.get<ISabeghe[]>(this.resourceUrl, { params: options, observe: 'response' }).pipe(
            map((res: EntityArrayResponseType) => {
                // return  this.convertDateArrayFromServer(res);
                return res;
            })
        );
    }
}
