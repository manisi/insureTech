/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratSalesMaliComponent } from 'app/entities/adam-khesarat-sales-mali/adam-khesarat-sales-mali.component';
import { AdamKhesaratSalesMaliService } from 'app/entities/adam-khesarat-sales-mali/adam-khesarat-sales-mali.service';
import { AdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';

describe('Component Tests', () => {
    describe('AdamKhesaratSalesMali Management Component', () => {
        let comp: AdamKhesaratSalesMaliComponent;
        let fixture: ComponentFixture<AdamKhesaratSalesMaliComponent>;
        let service: AdamKhesaratSalesMaliService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratSalesMaliComponent],
                providers: [
                    {
                        provide: ActivatedRoute,
                        useValue: {
                            data: {
                                subscribe: (fn: (value: Data) => void) =>
                                    fn({
                                        pagingParams: {
                                            predicate: 'id',
                                            reverse: false,
                                            page: 0
                                        }
                                    })
                            }
                        }
                    }
                ]
            })
                .overrideTemplate(AdamKhesaratSalesMaliComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdamKhesaratSalesMaliComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdamKhesaratSalesMaliService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AdamKhesaratSalesMali(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.adamKhesaratSalesMalis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });

        it('should load a page', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AdamKhesaratSalesMali(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.loadPage(1);

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.adamKhesaratSalesMalis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });

        it('should re-initialize the page', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AdamKhesaratSalesMali(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.loadPage(1);
            comp.reset();

            // THEN
            expect(comp.page).toEqual(0);
            expect(service.query).toHaveBeenCalledTimes(2);
            expect(comp.adamKhesaratSalesMalis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
        it('should calculate the sort attribute for an id', () => {
            // WHEN
            const result = comp.sort();

            // THEN
            expect(result).toEqual(['id,asc']);
        });

        it('should calculate the sort attribute for a non-id attribute', () => {
            // GIVEN
            comp.predicate = 'name';

            // WHEN
            const result = comp.sort();

            // THEN
            expect(result).toEqual(['name,asc', 'id']);
        });
    });
});
