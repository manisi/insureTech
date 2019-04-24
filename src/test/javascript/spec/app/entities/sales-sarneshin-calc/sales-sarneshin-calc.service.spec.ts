/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'jalali-moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SalesSarneshinCalcService } from 'app/entities/sales-sarneshin-calc/sales-sarneshin-calc.service';
import { ISalesSarneshinCalc, SalesSarneshinCalc } from 'app/shared/model/sales-sarneshin-calc.model';

describe('Service Tests', () => {
    describe('SalesSarneshinCalc Service', () => {
        let injector: TestBed;
        let service: SalesSarneshinCalcService;
        let httpMock: HttpTestingController;
        let elemDefault: ISalesSarneshinCalc;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(SalesSarneshinCalcService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new SalesSarneshinCalc(0, 0, 0, 0, currentDate, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        tarikhShoroo: currentDate.format(DATE_FORMAT),
                        tarikhPayan: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a SalesSarneshinCalc', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        tarikhShoroo: currentDate.format(DATE_FORMAT),
                        tarikhPayan: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        tarikhShoroo: currentDate,
                        tarikhPayan: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new SalesSarneshinCalc(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a SalesSarneshinCalc', async () => {
                const returnedFromService = Object.assign(
                    {
                        mablaghSalesMazad: 1,
                        tedadRooz: 1,
                        mablaghHaghBime: 1,
                        tarikhShoroo: currentDate.format(DATE_FORMAT),
                        tarikhPayan: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        tarikhShoroo: currentDate,
                        tarikhPayan: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of SalesSarneshinCalc', async () => {
                const returnedFromService = Object.assign(
                    {
                        mablaghSalesMazad: 1,
                        tedadRooz: 1,
                        mablaghHaghBime: 1,
                        tarikhShoroo: currentDate.format(DATE_FORMAT),
                        tarikhPayan: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        tarikhShoroo: currentDate,
                        tarikhPayan: currentDate
                    },
                    returnedFromService
                );
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

            it('should delete a SalesSarneshinCalc', async () => {
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
