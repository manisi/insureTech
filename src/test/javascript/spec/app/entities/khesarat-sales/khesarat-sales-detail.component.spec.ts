/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSalesDetailComponent } from 'app/entities/khesarat-sales/khesarat-sales-detail.component';
import { KhesaratSales } from 'app/shared/model/khesarat-sales.model';

describe('Component Tests', () => {
    describe('KhesaratSales Management Detail Component', () => {
        let comp: KhesaratSalesDetailComponent;
        let fixture: ComponentFixture<KhesaratSalesDetailComponent>;
        const route = ({ data: of({ khesaratSales: new KhesaratSales(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSalesDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KhesaratSalesDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhesaratSalesDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.khesaratSales).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
