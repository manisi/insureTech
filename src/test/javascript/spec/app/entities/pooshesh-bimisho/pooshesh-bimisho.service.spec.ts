/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PoosheshBimishoService } from 'app/entities/pooshesh-bimisho/pooshesh-bimisho.service';
import { IPoosheshBimisho, PoosheshBimisho } from 'app/shared/model/pooshesh-bimisho.model';

describe('Service Tests', () => {
    describe('PoosheshBimisho Service', () => {
        let injector: TestBed;
        let service: PoosheshBimishoService;
        let httpMock: HttpTestingController;
        let elemDefault: IPoosheshBimisho;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PoosheshBimishoService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new PoosheshBimisho(0, 'AAAAAAA', false, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        aztarikh: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a PoosheshBimisho', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        aztarikh: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        aztarikh: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new PoosheshBimisho(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a PoosheshBimisho', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        faal: true,
                        aztarikh: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        aztarikh: currentDate
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

            it('should return a list of PoosheshBimisho', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        faal: true,
                        aztarikh: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        aztarikh: currentDate
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

            it('should delete a PoosheshBimisho', async () => {
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
