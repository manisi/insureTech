/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'jalali-moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SalesJaniCalcService } from 'app/entities/sales-jani-calc/sales-jani-calc.service';
import { ISalesJaniCalc, SalesJaniCalc } from 'app/shared/model/sales-jani-calc.model';

describe('Service Tests', () => {
    describe('SalesJaniCalc Service', () => {
        let injector: TestBed;
        let service: SalesJaniCalcService;
        let httpMock: HttpTestingController;
        let elemDefault: ISalesJaniCalc;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(SalesJaniCalcService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new SalesJaniCalc(0, 0, 0, 0, currentDate, currentDate, 'AAAAAAA', 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        tarikhShoroFaaliat: currentDate.format(DATE_FORMAT),
                        tarikhPayanFaaliat: currentDate.format(DATE_FORMAT)
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

            it('should create a SalesJaniCalc', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        tarikhShoroFaaliat: currentDate.format(DATE_FORMAT),
                        tarikhPayanFaaliat: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        tarikhShoroFaaliat: currentDate,
                        tarikhPayanFaaliat: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new SalesJaniCalc(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a SalesJaniCalc', async () => {
                const returnedFromService = Object.assign(
                    {
                        mablaghJani: 1,
                        mablaghMaliEjbari: 1,
                        tedadRooz: 1,
                        tarikhShoroFaaliat: currentDate.format(DATE_FORMAT),
                        tarikhPayanFaaliat: currentDate.format(DATE_FORMAT),
                        naamSherkat: 'BBBBBB',
                        haghbime: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        tarikhShoroFaaliat: currentDate,
                        tarikhPayanFaaliat: currentDate
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

            it('should return a list of SalesJaniCalc', async () => {
                const returnedFromService = Object.assign(
                    {
                        mablaghJani: 1,
                        mablaghMaliEjbari: 1,
                        tedadRooz: 1,
                        tarikhShoroFaaliat: currentDate.format(DATE_FORMAT),
                        tarikhPayanFaaliat: currentDate.format(DATE_FORMAT),
                        naamSherkat: 'BBBBBB',
                        haghbime: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        tarikhShoroFaaliat: currentDate,
                        tarikhPayanFaaliat: currentDate
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

            it('should delete a SalesJaniCalc', async () => {
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
