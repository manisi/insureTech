import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKhodro } from 'app/shared/model/khodro.model';

type EntityResponseType = HttpResponse<IKhodro>;
type EntityArrayResponseType = HttpResponse<IKhodro[]>;

@Injectable({ providedIn: 'root' })
export class KhodroService {
    public resourceUrl = SERVER_API_URL + 'api/khodros';

    constructor(protected http: HttpClient) {}

    create(khodro: IKhodro): Observable<EntityResponseType> {
        return this.http.post<IKhodro>(this.resourceUrl, khodro, { observe: 'response' });
    }

    update(khodro: IKhodro): Observable<EntityResponseType> {
        return this.http.put<IKhodro>(this.resourceUrl, khodro, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKhodro>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKhodro[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
