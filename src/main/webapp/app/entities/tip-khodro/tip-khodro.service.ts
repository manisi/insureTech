import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipKhodro } from 'app/shared/model/tip-khodro.model';

type EntityResponseType = HttpResponse<ITipKhodro>;
type EntityArrayResponseType = HttpResponse<ITipKhodro[]>;

@Injectable({ providedIn: 'root' })
export class TipKhodroService {
    public resourceUrl = SERVER_API_URL + 'api/tip-khodros';

    constructor(protected http: HttpClient) {}

    create(tipKhodro: ITipKhodro): Observable<EntityResponseType> {
        return this.http.post<ITipKhodro>(this.resourceUrl, tipKhodro, { observe: 'response' });
    }

    update(tipKhodro: ITipKhodro): Observable<EntityResponseType> {
        return this.http.put<ITipKhodro>(this.resourceUrl, tipKhodro, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipKhodro>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipKhodro[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
