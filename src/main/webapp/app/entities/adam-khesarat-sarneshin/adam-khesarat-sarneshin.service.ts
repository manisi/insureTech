import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';
import { ILookup } from 'app/shared/model/lookup.model';

type EntityResponseType = HttpResponse<IAdamKhesaratSarneshin>;
type EntityArrayResponseType = HttpResponse<IAdamKhesaratSarneshin[]>;
type EntityArrayLookupResponseType = HttpResponse<ILookup[]>;

@Injectable({ providedIn: 'root' })
export class AdamKhesaratSarneshinService {
    public resourceUrl = SERVER_API_URL + 'api/adam-khesarat-sarneshins';
    public resourceUrlForLookup = this.resourceUrl + '-lookup';

    constructor(protected http: HttpClient) {}

    create(adamKhesaratSarneshin: IAdamKhesaratSarneshin): Observable<EntityResponseType> {
        return this.http.post<IAdamKhesaratSarneshin>(this.resourceUrl, adamKhesaratSarneshin, { observe: 'response' });
    }

    update(adamKhesaratSarneshin: IAdamKhesaratSarneshin): Observable<EntityResponseType> {
        return this.http.put<IAdamKhesaratSarneshin>(this.resourceUrl, adamKhesaratSarneshin, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAdamKhesaratSarneshin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAdamKhesaratSarneshin[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    lookup(req?: any): Observable<EntityArrayLookupResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILookup[]>(this.resourceUrlForLookup, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
