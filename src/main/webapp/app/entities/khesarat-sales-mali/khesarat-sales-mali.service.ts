import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';
import { ILookup } from 'app/shared/model/lookup.model';

type EntityResponseType = HttpResponse<IKhesaratSalesMali>;
type EntityArrayResponseType = HttpResponse<IKhesaratSalesMali[]>;
type EntityArrayLookupResponseType = HttpResponse<ILookup[]>;

@Injectable({ providedIn: 'root' })
export class KhesaratSalesMaliService {
    public resourceUrl = SERVER_API_URL + 'api/khesarat-sales-malis';
    public resourceUrlForLookup = this.resourceUrl + '-lookup';

    constructor(protected http: HttpClient) {}

    create(khesaratSalesMali: IKhesaratSalesMali): Observable<EntityResponseType> {
        return this.http.post<IKhesaratSalesMali>(this.resourceUrl, khesaratSalesMali, { observe: 'response' });
    }

    update(khesaratSalesMali: IKhesaratSalesMali): Observable<EntityResponseType> {
        return this.http.put<IKhesaratSalesMali>(this.resourceUrl, khesaratSalesMali, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKhesaratSalesMali>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKhesaratSalesMali[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    lookup(req?: any): Observable<EntityArrayLookupResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILookup[]>(this.resourceUrlForLookup, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
