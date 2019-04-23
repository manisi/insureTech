/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { MoredEstefadeSalesDetailComponent } from 'app/entities/mored-estefade-sales/mored-estefade-sales-detail.component';
import { MoredEstefadeSales } from 'app/shared/model/mored-estefade-sales.model';

describe('Component Tests', () => {
    describe('MoredEstefadeSales Management Detail Component', () => {
        let comp: MoredEstefadeSalesDetailComponent;
        let fixture: ComponentFixture<MoredEstefadeSalesDetailComponent>;
        const route = ({ data: of({ moredEstefadeSales: new MoredEstefadeSales(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [MoredEstefadeSalesDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MoredEstefadeSalesDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MoredEstefadeSalesDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.moredEstefadeSales).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
