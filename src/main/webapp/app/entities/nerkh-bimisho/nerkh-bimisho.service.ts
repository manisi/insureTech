import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';

type EntityResponseType = HttpResponse<INerkhBimisho>;
type EntityArrayResponseType = HttpResponse<INerkhBimisho[]>;

@Injectable({ providedIn: 'root' })
export class NerkhBimishoService {
    public resourceUrl = SERVER_API_URL + 'api/nerkhs';

    constructor(protected http: HttpClient) {}

    create(nerkh: INerkhBimisho): Observable<EntityResponseType> {
        return this.http.post<INerkhBimisho>(this.resourceUrl, nerkh, { observe: 'response' });
    }

    update(nerkh: INerkhBimisho): Observable<EntityResponseType> {
        return this.http.put<INerkhBimisho>(this.resourceUrl, nerkh, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INerkhBimisho>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INerkhBimisho[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
