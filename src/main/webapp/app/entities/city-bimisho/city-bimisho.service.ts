import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICityBimisho } from 'app/shared/model/city-bimisho.model';

type EntityResponseType = HttpResponse<ICityBimisho>;
type EntityArrayResponseType = HttpResponse<ICityBimisho[]>;

@Injectable({ providedIn: 'root' })
export class CityBimishoService {
    public resourceUrl = SERVER_API_URL + 'api/cities';

    constructor(protected http: HttpClient) {}

    create(city: ICityBimisho): Observable<EntityResponseType> {
        return this.http.post<ICityBimisho>(this.resourceUrl, city, { observe: 'response' });
    }

    update(city: ICityBimisho): Observable<EntityResponseType> {
        return this.http.put<ICityBimisho>(this.resourceUrl, city, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICityBimisho>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICityBimisho[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
