/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { CityBimishoDetailComponent } from 'app/entities/city-bimisho/city-bimisho-detail.component';
import { CityBimisho } from 'app/shared/model/city-bimisho.model';

describe('Component Tests', () => {
    describe('CityBimisho Management Detail Component', () => {
        let comp: CityBimishoDetailComponent;
        let fixture: ComponentFixture<CityBimishoDetailComponent>;
        const route = ({ data: of({ city: new CityBimisho(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [CityBimishoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CityBimishoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CityBimishoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.city).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
