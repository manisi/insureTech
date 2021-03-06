import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGroupOperationsData } from 'app/shared/model/group-operations-data.model';

type EntityResponseType = HttpResponse<IGroupOperationsData>;
type EntityArrayResponseType = HttpResponse<IGroupOperationsData[]>;

@Injectable({ providedIn: 'root' })
export class GroupOperationsDataService {
    public resourceUrl = SERVER_API_URL + 'api/group-operations-data';

    constructor(protected http: HttpClient) {}

    create(groupOperationsData: IGroupOperationsData): Observable<EntityResponseType> {
        return this.http.post<IGroupOperationsData>(this.resourceUrl, groupOperationsData, { observe: 'response' });
    }

    update(groupOperationsData: IGroupOperationsData): Observable<EntityResponseType> {
        return this.http.put<IGroupOperationsData>(this.resourceUrl, groupOperationsData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGroupOperationsData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGroupOperationsData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    applyfile(id: number): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
