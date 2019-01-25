import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKohnegi } from 'app/shared/model/kohnegi.model';

type EntityResponseType = HttpResponse<IKohnegi>;
type EntityArrayResponseType = HttpResponse<IKohnegi[]>;

@Injectable({ providedIn: 'root' })
export class KohnegiService {
    public resourceUrl = SERVER_API_URL + 'api/kohnegis';

    constructor(protected http: HttpClient) {}

    create(kohnegi: IKohnegi): Observable<EntityResponseType> {
        return this.http.post<IKohnegi>(this.resourceUrl, kohnegi, { observe: 'response' });
    }

    update(kohnegi: IKohnegi): Observable<EntityResponseType> {
        return this.http.put<IKohnegi>(this.resourceUrl, kohnegi, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKohnegi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKohnegi[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
