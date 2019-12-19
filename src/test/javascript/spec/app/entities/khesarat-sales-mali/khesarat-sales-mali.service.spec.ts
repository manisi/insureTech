/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { KhesaratSalesMaliService } from 'app/entities/khesarat-sales-mali/khesarat-sales-mali.service';
import { IKhesaratSalesMali, KhesaratSalesMali, SalesSarneshinEnum } from 'app/shared/model/khesarat-sales-mali.model';

describe('Service Tests', () => {
    describe('KhesaratSalesMali Service', () => {
        let injector: TestBed;
        let service: KhesaratSalesMaliService;
        let httpMock: HttpTestingController;
        let elemDefault: IKhesaratSalesMali;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(KhesaratSalesMaliService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new KhesaratSalesMali(0, SalesSarneshinEnum.SALES, 0, false);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a KhesaratSalesMali', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new KhesaratSalesMali(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a KhesaratSalesMali', async () => {
                const returnedFromService = Object.assign(
                    {
                        noe: 'BBBBBB',
                        nerkhKhesaratMali: 1,
                        faal: true
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of KhesaratSalesMali', async () => {
                const returnedFromService = Object.assign(
                    {
                        noe: 'BBBBBB',
                        nerkhKhesaratMali: 1,
                        faal: true
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a KhesaratSalesMali', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
