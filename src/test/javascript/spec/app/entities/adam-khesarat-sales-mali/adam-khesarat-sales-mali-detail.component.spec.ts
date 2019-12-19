/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratSalesMaliDetailComponent } from 'app/entities/adam-khesarat-sales-mali/adam-khesarat-sales-mali-detail.component';
import { AdamKhesaratSalesMali } from 'app/shared/model/adam-khesarat-sales-mali.model';

describe('Component Tests', () => {
    describe('AdamKhesaratSalesMali Management Detail Component', () => {
        let comp: AdamKhesaratSalesMaliDetailComponent;
        let fixture: ComponentFixture<AdamKhesaratSalesMaliDetailComponent>;
        const route = ({ data: of({ adamKhesaratSalesMali: new AdamKhesaratSalesMali(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratSalesMaliDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AdamKhesaratSalesMaliDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdamKhesaratSalesMaliDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.adamKhesaratSalesMali).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
