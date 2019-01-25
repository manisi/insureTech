import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMohasebeBadane } from 'app/shared/model/mohasebe-badane.model';

type EntityResponseType = HttpResponse<IMohasebeBadane>;
type EntityArrayResponseType = HttpResponse<IMohasebeBadane[]>;

@Injectable({ providedIn: 'root' })
export class MohasebeBadaneService {
    public resourceUrl = SERVER_API_URL + 'api/mohasebe-badanes';

    constructor(protected http: HttpClient) {}

    create(mohasebeBadane: IMohasebeBadane): Observable<EntityResponseType> {
        return this.http.post<IMohasebeBadane>(this.resourceUrl, mohasebeBadane, { observe: 'response' });
    }

    update(mohasebeBadane: IMohasebeBadane): Observable<EntityResponseType> {
        return this.http.put<IMohasebeBadane>(this.resourceUrl, mohasebeBadane, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMohasebeBadane>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMohasebeBadane[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
