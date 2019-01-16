import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipKhodroBimisho } from 'app/shared/model/tip-khodro-bimisho.model';

type EntityResponseType = HttpResponse<ITipKhodroBimisho>;
type EntityArrayResponseType = HttpResponse<ITipKhodroBimisho[]>;

@Injectable({ providedIn: 'root' })
export class TipKhodroBimishoService {
    public resourceUrl = SERVER_API_URL + 'api/tip-khodros';

    constructor(protected http: HttpClient) {}

    create(tipKhodro: ITipKhodroBimisho): Observable<EntityResponseType> {
        return this.http.post<ITipKhodroBimisho>(this.resourceUrl, tipKhodro, { observe: 'response' });
    }

    update(tipKhodro: ITipKhodroBimisho): Observable<EntityResponseType> {
        return this.http.put<ITipKhodroBimisho>(this.resourceUrl, tipKhodro, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipKhodroBimisho>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipKhodroBimisho[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
