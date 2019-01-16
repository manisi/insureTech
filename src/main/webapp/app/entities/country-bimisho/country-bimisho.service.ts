import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICountryBimisho } from 'app/shared/model/country-bimisho.model';

type EntityResponseType = HttpResponse<ICountryBimisho>;
type EntityArrayResponseType = HttpResponse<ICountryBimisho[]>;

@Injectable({ providedIn: 'root' })
export class CountryBimishoService {
    public resourceUrl = SERVER_API_URL + 'api/countries';

    constructor(protected http: HttpClient) {}

    create(country: ICountryBimisho): Observable<EntityResponseType> {
        return this.http.post<ICountryBimisho>(this.resourceUrl, country, { observe: 'response' });
    }

    update(country: ICountryBimisho): Observable<EntityResponseType> {
        return this.http.put<ICountryBimisho>(this.resourceUrl, country, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICountryBimisho>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICountryBimisho[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
