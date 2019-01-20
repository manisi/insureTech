/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { MohasebeSalesDetailComponent } from 'app/entities/mohasebe-sales/mohasebe-sales-detail.component';
import { MohasebeSales } from 'app/shared/model/mohasebe-sales.model';

describe('Component Tests', () => {
    describe('MohasebeSales Management Detail Component', () => {
        let comp: MohasebeSalesDetailComponent;
        let fixture: ComponentFixture<MohasebeSalesDetailComponent>;
        const route = ({ data: of({ mohasebeSales: new MohasebeSales(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [MohasebeSalesDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MohasebeSalesDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MohasebeSalesDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mohasebeSales).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
