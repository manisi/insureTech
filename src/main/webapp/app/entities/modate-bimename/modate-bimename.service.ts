import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IModateBimename } from 'app/shared/model/modate-bimename.model';

type EntityResponseType = HttpResponse<IModateBimename>;
type EntityArrayResponseType = HttpResponse<IModateBimename[]>;

@Injectable({ providedIn: 'root' })
export class ModateBimenameService {
    public resourceUrl = SERVER_API_URL + 'api/modate-bimenames';

    constructor(protected http: HttpClient) {}

    create(modateBimename: IModateBimename): Observable<EntityResponseType> {
        return this.http.post<IModateBimename>(this.resourceUrl, modateBimename, { observe: 'response' });
    }

    update(modateBimename: IModateBimename): Observable<EntityResponseType> {
        return this.http.put<IModateBimename>(this.resourceUrl, modateBimename, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IModateBimename>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IModateBimename[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
