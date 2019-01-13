import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IControl } from 'app/shared/model/control.model';

type EntityResponseType = HttpResponse<IControl>;
type EntityArrayResponseType = HttpResponse<IControl[]>;

@Injectable({ providedIn: 'root' })
export class ControlService {
    public resourceUrl = SERVER_API_URL + 'api/controls';

    constructor(protected http: HttpClient) {}

    create(control: IControl): Observable<EntityResponseType> {
        return this.http.post<IControl>(this.resourceUrl, control, { observe: 'response' });
    }

    update(control: IControl): Observable<EntityResponseType> {
        return this.http.put<IControl>(this.resourceUrl, control, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IControl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IControl[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
