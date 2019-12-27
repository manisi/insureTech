import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKhesaratSales } from 'app/shared/model/khesarat-sales.model';
import { ILookup } from 'app/shared/model/lookup.model';

type EntityResponseType = HttpResponse<IKhesaratSales>;
type EntityArrayResponseType = HttpResponse<IKhesaratSales[]>;
type EntityArrayResponseTypeLookup = HttpResponse<ILookup[]>;

@Injectable({ providedIn: 'root' })
export class KhesaratSalesService {
    public resourceUrl = SERVER_API_URL + 'api/khesarat-sales';
    public resourceUrlForLookup = this.resourceUrl + '-lookup';

    constructor(protected http: HttpClient) {}

    create(khesaratSales: IKhesaratSales): Observable<EntityResponseType> {
        return this.http.post<IKhesaratSales>(this.resourceUrl, khesaratSales, { observe: 'response' });
    }

    update(khesaratSales: IKhesaratSales): Observable<EntityResponseType> {
        return this.http.put<IKhesaratSales>(this.resourceUrl, khesaratSales, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKhesaratSales>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKhesaratSales[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    lookup(req?: any): Observable<EntityArrayResponseTypeLookup> {
        const options = createRequestOption(req);
        return this.http.get<ILookup[]>(this.resourceUrlForLookup, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
