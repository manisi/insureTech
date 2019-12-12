import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';

type EntityResponseType = HttpResponse<IEstelaamSalesNerkh>;
type EntityArrayResponseType = HttpResponse<IEstelaamSalesNerkh[]>;

@Injectable({ providedIn: 'root' })
export class EstelaamSalesNerkhService {
    public resourceUrl = SERVER_API_URL + 'api/estelaam-sales-nerkhs';

    constructor(protected http: HttpClient) {}

    create(estelaamSalesNerkh: IEstelaamSalesNerkh): Observable<EntityResponseType> {
        return this.http.post<IEstelaamSalesNerkh>(this.resourceUrl, estelaamSalesNerkh, { observe: 'response' });
    }

    update(estelaamSalesNerkh: IEstelaamSalesNerkh): Observable<EntityResponseType> {
        return this.http.put<IEstelaamSalesNerkh>(this.resourceUrl, estelaamSalesNerkh, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEstelaamSalesNerkh>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEstelaamSalesNerkh[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
