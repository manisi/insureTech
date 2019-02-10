/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SabegheService } from 'app/entities/sabeghe/sabeghe.service';
import { ISabeghe, Sabeghe } from 'app/shared/model/sabeghe.model';

describe('Service Tests', () => {
    describe('Sabeghe Service', () => {
        let injector: TestBed;
        let service: SabegheService;
        let httpMock: HttpTestingController;
        let elemDefault: ISabeghe;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(SabegheService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Sabeghe(0, 'AAAAAAA', 'AAAAAAA', false, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        tarikh: currentDate.format(DATE_FORMAT)
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

            it('should create a Sabeghe', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        tarikh: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        tarikh: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Sabeghe(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Sabeghe', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        sharh: 'BBBBBB',
                        faal: true,
                        tarikh: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        tarikh: currentDate
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

            it('should return a list of Sabeghe', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        sharh: 'BBBBBB',
                        faal: true,
                        tarikh: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        tarikh: currentDate
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

            it('should delete a Sabeghe', async () => {
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
