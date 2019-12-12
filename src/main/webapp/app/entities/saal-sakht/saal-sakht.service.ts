import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISaalSakht } from 'app/shared/model/saal-sakht.model';

type EntityResponseType = HttpResponse<ISaalSakht>;
type EntityArrayResponseType = HttpResponse<ISaalSakht[]>;

@Injectable({ providedIn: 'root' })
export class SaalSakhtService {
    public resourceUrl = SERVER_API_URL + 'api/saal-sakhts';

    constructor(protected http: HttpClient) {}

    create(saalSakht: ISaalSakht): Observable<EntityResponseType> {
        return this.http.post<ISaalSakht>(this.resourceUrl, saalSakht, { observe: 'response' });
    }

    update(saalSakht: ISaalSakht): Observable<EntityResponseType> {
        return this.http.put<ISaalSakht>(this.resourceUrl, saalSakht, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISaalSakht>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISaalSakht[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
