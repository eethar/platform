import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams, BaseRequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Donation } from './donation.model';
import { DateUtils } from 'ng-jhipster';
@Injectable()
export class DonationService {

    private resourceUrl = 'resources/api/donation';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    create(donation: Donation): Observable<Donation> {
        const copy: Donation = Object.assign({}, donation);
        copy.donationDate = this.dateUtils.toDate(donation.donationDate);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(donation: Donation): Observable<Donation> {
        const copy: Donation = Object.assign({}, donation);

        copy.donationDate = this.dateUtils.toDate(donation.donationDate);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Donation> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            jsonResponse.donationDate = this.dateUtils
                .convertDateTimeFromServer(jsonResponse.donationDate);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<Response> {
        const options = this.createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: any) => this.convertResponse(res))
        ;
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: any): any {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            jsonResponse[i].donationDate = this.dateUtils
                .convertDateTimeFromServer(jsonResponse[i].donationDate);
        }
        res._body = jsonResponse;
        return res;
    }

    private createRequestOption(req?: any): BaseRequestOptions {
        const options: BaseRequestOptions = new BaseRequestOptions();
        if (req) {
            const params: URLSearchParams = new URLSearchParams();
            params.set('page', req.page);
            params.set('size', req.size);
            if (req.sort) {
                params.paramsMap.set('sort', req.sort);
            }
            params.set('query', req.query);

            options.search = params;
        }
        return options;
    }
}
