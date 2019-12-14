import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVaziatBime } from 'app/shared/model/vaziat-bime.model';

type EntityResponseType = HttpResponse<IVaziatBime>;
type EntityArrayResponseType = HttpResponse<IVaziatBime[]>;

@Injectable({ providedIn: 'root' })
export class VaziatBimeService {
    public resourceUrl = SERVER_API_URL + 'api/vaziat-bimes';

    constructor(protected http: HttpClient) {}

    create(vaziatBime: IVaziatBime): Observable<EntityResponseType> {
        return this.http.post<IVaziatBime>(this.resourceUrl, vaziatBime, { observe: 'response' });
    }

    update(vaziatBime: IVaziatBime): Observable<EntityResponseType> {
        return this.http.put<IVaziatBime>(this.resourceUrl, vaziatBime, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVaziatBime>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVaziatBime[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
