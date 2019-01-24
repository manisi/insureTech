import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJarimeDirkard } from 'app/shared/model/jarime-dirkard.model';

type EntityResponseType = HttpResponse<IJarimeDirkard>;
type EntityArrayResponseType = HttpResponse<IJarimeDirkard[]>;

@Injectable({ providedIn: 'root' })
export class JarimeDirkardService {
    public resourceUrl = SERVER_API_URL + 'api/jarime-dirkards';

    constructor(protected http: HttpClient) {}

    create(jarimeDirkard: IJarimeDirkard): Observable<EntityResponseType> {
        return this.http.post<IJarimeDirkard>(this.resourceUrl, jarimeDirkard, { observe: 'response' });
    }

    update(jarimeDirkard: IJarimeDirkard): Observable<EntityResponseType> {
        return this.http.put<IJarimeDirkard>(this.resourceUrl, jarimeDirkard, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IJarimeDirkard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IJarimeDirkard[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
