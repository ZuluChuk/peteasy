import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { OrderEntryPe } from './order-entry-pe.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class OrderEntryPeService {

    private resourceUrl = 'api/order-entries';
    private resourceSearchUrl = 'api/_search/order-entries';

    constructor(private http: Http) { }

    create(orderEntry: OrderEntryPe): Observable<OrderEntryPe> {
        const copy = this.convert(orderEntry);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(orderEntry: OrderEntryPe): Observable<OrderEntryPe> {
        const copy = this.convert(orderEntry);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<OrderEntryPe> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(orderEntry: OrderEntryPe): OrderEntryPe {
        const copy: OrderEntryPe = Object.assign({}, orderEntry);
        return copy;
    }
}
