import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';

type EntityResponseType = HttpResponse<IGrouhKhodro>;
type EntityArrayResponseType = HttpResponse<IGrouhKhodro[]>;

@Injectable({ providedIn: 'root' })
export class GrouhKhodroService {
    public resourceUrl = SERVER_API_URL + 'api/grouh-khodros';

    constructor(protected http: HttpClient) {}

    create(grouhKhodro: IGrouhKhodro): Observable<EntityResponseType> {
        return this.http.post<IGrouhKhodro>(this.resourceUrl, grouhKhodro, { observe: 'response' });
    }

    update(grouhKhodro: IGrouhKhodro): Observable<EntityResponseType> {
        return this.http.put<IGrouhKhodro>(this.resourceUrl, grouhKhodro, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGrouhKhodro>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGrouhKhodro[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
