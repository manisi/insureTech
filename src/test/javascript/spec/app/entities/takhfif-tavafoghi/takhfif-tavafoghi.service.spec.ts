/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'jalali-moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TakhfifTavafoghiService } from 'app/entities/takhfif-tavafoghi/takhfif-tavafoghi.service';
import { ITakhfifTavafoghi, TakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';

describe('Service Tests', () => {
    describe('TakhfifTavafoghi Service', () => {
        let injector: TestBed;
        let service: TakhfifTavafoghiService;
        let httpMock: HttpTestingController;
        let elemDefault: ITakhfifTavafoghi;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(TakhfifTavafoghiService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new TakhfifTavafoghi(0, 'AAAAAAA', 0, currentDate, false);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        azTarikh: currentDate.format(DATE_FORMAT)
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

            it('should create a TakhfifTavafoghi', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        azTarikh: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        azTarikh: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new TakhfifTavafoghi(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a TakhfifTavafoghi', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        darsadTakhfif: 1,
                        azTarikh: currentDate.format(DATE_FORMAT),
                        faal: true
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        azTarikh: currentDate
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

            it('should return a list of TakhfifTavafoghi', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        darsadTakhfif: 1,
                        azTarikh: currentDate.format(DATE_FORMAT),
                        faal: true
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        azTarikh: currentDate
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

            it('should delete a TakhfifTavafoghi', async () => {
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
