import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMohasebeSales } from 'app/shared/model/mohasebe-sales.model';

type EntityResponseType = HttpResponse<IMohasebeSales>;
type EntityArrayResponseType = HttpResponse<IMohasebeSales[]>;

@Injectable({ providedIn: 'root' })
export class MohasebeSalesService {
    public resourceUrl = SERVER_API_URL + 'api/mohasebe-sales';

    constructor(protected http: HttpClient) {}

    create(mohasebeSales: IMohasebeSales): Observable<EntityResponseType> {
        return this.http.post<IMohasebeSales>(this.resourceUrl, mohasebeSales, { observe: 'response' });
    }

    update(mohasebeSales: IMohasebeSales): Observable<EntityResponseType> {
        return this.http.put<IMohasebeSales>(this.resourceUrl, mohasebeSales, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMohasebeSales>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMohasebeSales[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
