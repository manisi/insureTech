import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISherkatBime } from 'app/shared/model/sherkat-bime.model';
import { ILookup } from 'app/shared/model/lookup.model';

type EntityResponseType = HttpResponse<ISherkatBime>;
type EntityArrayResponseType = HttpResponse<ISherkatBime[]>;
type EntityArrayLookupResponseType = HttpResponse<ILookup[]>;

@Injectable({ providedIn: 'root' })
export class SherkatBimeService {
    public resourceUrl = SERVER_API_URL + 'api/sherkat-bimes';
    public resourceUrlForLookup = this.resourceUrl + '-lookup';

    constructor(protected http: HttpClient) {}

    create(sherkatBime: ISherkatBime): Observable<EntityResponseType> {
        return this.http.post<ISherkatBime>(this.resourceUrl, sherkatBime, { observe: 'response' });
    }

    update(sherkatBime: ISherkatBime): Observable<EntityResponseType> {
        return this.http.put<ISherkatBime>(this.resourceUrl, sherkatBime, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISherkatBime>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISherkatBime[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    lookup(req?: any): Observable<EntityArrayLookupResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILookup[]>(this.resourceUrlForLookup, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
