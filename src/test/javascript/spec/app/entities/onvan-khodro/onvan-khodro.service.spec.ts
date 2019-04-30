/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'jalali-moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { OnvanKhodroService } from 'app/entities/onvan-khodro/onvan-khodro.service';
import { IOnvanKhodro, OnvanKhodro } from 'app/shared/model/onvan-khodro.model';

describe('Service Tests', () => {
    describe('OnvanKhodro Service', () => {
        let injector: TestBed;
        let service: OnvanKhodroService;
        let httpMock: HttpTestingController;
        let elemDefault: IOnvanKhodro;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(OnvanKhodroService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new OnvanKhodro(0, 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, false);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        azTarikh: currentDate.format(DATE_FORMAT),
                        taTarikh: currentDate.format(DATE_FORMAT)
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

            it('should create a OnvanKhodro', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        azTarikh: currentDate.format(DATE_FORMAT),
                        taTarikh: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        azTarikh: currentDate,
                        taTarikh: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new OnvanKhodro(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a OnvanKhodro', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        sharh: 'BBBBBB',
                        azTarikh: currentDate.format(DATE_FORMAT),
                        taTarikh: currentDate.format(DATE_FORMAT),
                        faal: true
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        azTarikh: currentDate,
                        taTarikh: currentDate
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

            it('should return a list of OnvanKhodro', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        sharh: 'BBBBBB',
                        azTarikh: currentDate.format(DATE_FORMAT),
                        taTarikh: currentDate.format(DATE_FORMAT),
                        faal: true
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        azTarikh: currentDate,
                        taTarikh: currentDate
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

            it('should delete a OnvanKhodro', async () => {
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
