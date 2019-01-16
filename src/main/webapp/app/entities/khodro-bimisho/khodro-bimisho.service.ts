import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKhodroBimisho } from 'app/shared/model/khodro-bimisho.model';

type EntityResponseType = HttpResponse<IKhodroBimisho>;
type EntityArrayResponseType = HttpResponse<IKhodroBimisho[]>;

@Injectable({ providedIn: 'root' })
export class KhodroBimishoService {
    public resourceUrl = SERVER_API_URL + 'api/khodros';

    constructor(protected http: HttpClient) {}

    create(khodro: IKhodroBimisho): Observable<EntityResponseType> {
        return this.http.post<IKhodroBimisho>(this.resourceUrl, khodro, { observe: 'response' });
    }

    update(khodro: IKhodroBimisho): Observable<EntityResponseType> {
        return this.http.put<IKhodroBimisho>(this.resourceUrl, khodro, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKhodroBimisho>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKhodroBimisho[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
