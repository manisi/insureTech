import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKhesaratSrneshin } from 'app/shared/model/khesarat-srneshin.model';

type EntityResponseType = HttpResponse<IKhesaratSrneshin>;
type EntityArrayResponseType = HttpResponse<IKhesaratSrneshin[]>;

@Injectable({ providedIn: 'root' })
export class KhesaratSrneshinService {
    public resourceUrl = SERVER_API_URL + 'api/khesarat-srneshins';

    constructor(protected http: HttpClient) {}

    create(khesaratSrneshin: IKhesaratSrneshin): Observable<EntityResponseType> {
        return this.http.post<IKhesaratSrneshin>(this.resourceUrl, khesaratSrneshin, { observe: 'response' });
    }

    update(khesaratSrneshin: IKhesaratSrneshin): Observable<EntityResponseType> {
        return this.http.put<IKhesaratSrneshin>(this.resourceUrl, khesaratSrneshin, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKhesaratSrneshin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKhesaratSrneshin[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
