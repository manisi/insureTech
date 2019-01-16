/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { CountryBimishoDetailComponent } from 'app/entities/country-bimisho/country-bimisho-detail.component';
import { CountryBimisho } from 'app/shared/model/country-bimisho.model';

describe('Component Tests', () => {
    describe('CountryBimisho Management Detail Component', () => {
        let comp: CountryBimishoDetailComponent;
        let fixture: ComponentFixture<CountryBimishoDetailComponent>;
        const route = ({ data: of({ country: new CountryBimisho(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [CountryBimishoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CountryBimishoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CountryBimishoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.country).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
