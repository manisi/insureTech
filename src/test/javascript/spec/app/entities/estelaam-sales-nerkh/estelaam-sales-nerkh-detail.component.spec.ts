/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { EstelaamSalesNerkhDetailComponent } from 'app/entities/estelaam-sales-nerkh/estelaam-sales-nerkh-detail.component';
import { EstelaamSalesNerkh } from 'app/shared/model/estelaam-sales-nerkh.model';

describe('Component Tests', () => {
    describe('EstelaamSalesNerkh Management Detail Component', () => {
        let comp: EstelaamSalesNerkhDetailComponent;
        let fixture: ComponentFixture<EstelaamSalesNerkhDetailComponent>;
        const route = ({ data: of({ estelaamSalesNerkh: new EstelaamSalesNerkh(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [EstelaamSalesNerkhDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EstelaamSalesNerkhDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EstelaamSalesNerkhDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.estelaamSalesNerkh).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
