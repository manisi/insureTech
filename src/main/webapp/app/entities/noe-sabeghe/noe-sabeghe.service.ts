import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INoeSabeghe } from 'app/shared/model/noe-sabeghe.model';

type EntityResponseType = HttpResponse<INoeSabeghe>;
type EntityArrayResponseType = HttpResponse<INoeSabeghe[]>;

@Injectable({ providedIn: 'root' })
export class NoeSabegheService {
    public resourceUrl = SERVER_API_URL + 'api/noe-sabeghes';

    constructor(protected http: HttpClient) {}

    create(noeSabeghe: INoeSabeghe): Observable<EntityResponseType> {
        return this.http.post<INoeSabeghe>(this.resourceUrl, noeSabeghe, { observe: 'response' });
    }

    update(noeSabeghe: INoeSabeghe): Observable<EntityResponseType> {
        return this.http.put<INoeSabeghe>(this.resourceUrl, noeSabeghe, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INoeSabeghe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INoeSabeghe[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
