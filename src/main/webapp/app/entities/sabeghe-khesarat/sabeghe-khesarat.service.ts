import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';
import { ILookup } from 'app/shared/model/lookup.model';

type EntityResponseType = HttpResponse<ISabegheKhesarat>;
type EntityArrayResponseType = HttpResponse<ISabegheKhesarat[]>;
type EntityArrayLookupResponseType = HttpResponse<ILookup[]>;

@Injectable({ providedIn: 'root' })
export class SabegheKhesaratService {
    public resourceUrl = SERVER_API_URL + 'api/sabeghe-khesarats';
    public resourceUrlForLookup = this.resourceUrl + '-lookup';

    constructor(protected http: HttpClient) {}

    create(sabegheKhesarat: ISabegheKhesarat): Observable<EntityResponseType> {
        return this.http.post<ISabegheKhesarat>(this.resourceUrl, sabegheKhesarat, { observe: 'response' });
    }

    update(sabegheKhesarat: ISabegheKhesarat): Observable<EntityResponseType> {
        return this.http.put<ISabegheKhesarat>(this.resourceUrl, sabegheKhesarat, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISabegheKhesarat>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISabegheKhesarat[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    lookup(req?: any): Observable<EntityArrayLookupResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILookup[]>(this.resourceUrlForLookup, { params: options, observe: 'response' });
    }
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
