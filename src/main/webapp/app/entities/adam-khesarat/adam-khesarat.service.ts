import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdamKhesarat } from 'app/shared/model/adam-khesarat.model';
import { ILookup } from 'app/shared/model/lookup.model';

type EntityResponseType = HttpResponse<IAdamKhesarat>;
type EntityArrayResponseType = HttpResponse<IAdamKhesarat[]>;
type EntityArrayLookupResponseType = HttpResponse<ILookup[]>;

@Injectable({ providedIn: 'root' })
export class AdamKhesaratService {
    public resourceUrl = SERVER_API_URL + 'api/adam-khesarats';
    public resourceUrlForLookup = this.resourceUrl + '-lookup';

    constructor(protected http: HttpClient) {}

    create(adamKhesarat: IAdamKhesarat): Observable<EntityResponseType> {
        return this.http.post<IAdamKhesarat>(this.resourceUrl, adamKhesarat, { observe: 'response' });
    }

    update(adamKhesarat: IAdamKhesarat): Observable<EntityResponseType> {
        return this.http.put<IAdamKhesarat>(this.resourceUrl, adamKhesarat, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAdamKhesarat>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAdamKhesarat[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    lookup(req?: any): Observable<EntityArrayLookupResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILookup[]>(this.resourceUrlForLookup, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
