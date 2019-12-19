import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';

type EntityResponseType = HttpResponse<IAdamKhesaratSalesMali>;
type EntityArrayResponseType = HttpResponse<IAdamKhesaratSalesMali[]>;

@Injectable({ providedIn: 'root' })
export class AdamKhesaratSalesMaliService {
    public resourceUrl = SERVER_API_URL + 'api/adam-khesarat-sales-malis';

    constructor(protected http: HttpClient) {}

    create(adamKhesaratSalesMali: IAdamKhesaratSalesMali): Observable<EntityResponseType> {
        return this.http.post<IAdamKhesaratSalesMali>(this.resourceUrl, adamKhesaratSalesMali, { observe: 'response' });
    }

    update(adamKhesaratSalesMali: IAdamKhesaratSalesMali): Observable<EntityResponseType> {
        return this.http.put<IAdamKhesaratSalesMali>(this.resourceUrl, adamKhesaratSalesMali, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAdamKhesaratSalesMali>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAdamKhesaratSalesMali[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
