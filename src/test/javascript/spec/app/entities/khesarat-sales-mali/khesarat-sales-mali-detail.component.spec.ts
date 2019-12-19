/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSalesMaliDetailComponent } from 'app/entities/khesarat-sales-mali/khesarat-sales-mali-detail.component';
import { KhesaratSalesMali } from 'app/shared/model/khesarat-sales-mali.model';

describe('Component Tests', () => {
    describe('KhesaratSalesMali Management Detail Component', () => {
        let comp: KhesaratSalesMaliDetailComponent;
        let fixture: ComponentFixture<KhesaratSalesMaliDetailComponent>;
        const route = ({ data: of({ khesaratSalesMali: new KhesaratSalesMali(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSalesMaliDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KhesaratSalesMaliDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhesaratSalesMaliDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.khesaratSalesMali).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
