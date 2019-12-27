import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAnvaeKhodro } from 'app/shared/model/anvae-khodro.model';
import { ILookup } from 'app/shared/model/lookup.model';

type EntityResponseType = HttpResponse<IAnvaeKhodro>;
type EntityArrayResponseType = HttpResponse<IAnvaeKhodro[]>;
type EntityArrayLookupResponseType = HttpResponse<ILookup[]>;

@Injectable({ providedIn: 'root' })
export class AnvaeKhodroService {
    public resourceUrl = SERVER_API_URL + 'api/anvae-khodros';
    public resourceUrlForLookup = this.resourceUrl + '-lookup';

    constructor(protected http: HttpClient) {}

    create(anvaeKhodro: IAnvaeKhodro): Observable<EntityResponseType> {
        return this.http.post<IAnvaeKhodro>(this.resourceUrl, anvaeKhodro, { observe: 'response' });
    }

    update(anvaeKhodro: IAnvaeKhodro): Observable<EntityResponseType> {
        return this.http.put<IAnvaeKhodro>(this.resourceUrl, anvaeKhodro, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAnvaeKhodro>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAnvaeKhodro[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    lookup(req?: any): Observable<EntityArrayLookupResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILookup[]>(this.resourceUrlForLookup, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
