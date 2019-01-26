import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';

type EntityResponseType = HttpResponse<IAdamKhesaratBadane>;
type EntityArrayResponseType = HttpResponse<IAdamKhesaratBadane[]>;

@Injectable({ providedIn: 'root' })
export class AdamKhesaratBadaneService {
    public resourceUrl = SERVER_API_URL + 'api/adam-khesarat-badanes';

    constructor(protected http: HttpClient) {}

    create(adamKhesaratBadane: IAdamKhesaratBadane): Observable<EntityResponseType> {
        return this.http.post<IAdamKhesaratBadane>(this.resourceUrl, adamKhesaratBadane, { observe: 'response' });
    }

    update(adamKhesaratBadane: IAdamKhesaratBadane): Observable<EntityResponseType> {
        return this.http.put<IAdamKhesaratBadane>(this.resourceUrl, adamKhesaratBadane, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAdamKhesaratBadane>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAdamKhesaratBadane[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
