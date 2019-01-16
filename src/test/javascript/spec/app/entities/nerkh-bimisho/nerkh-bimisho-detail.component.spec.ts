/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { NerkhBimishoDetailComponent } from 'app/entities/nerkh-bimisho/nerkh-bimisho-detail.component';
import { NerkhBimisho } from 'app/shared/model/nerkh-bimisho.model';

describe('Component Tests', () => {
    describe('NerkhBimisho Management Detail Component', () => {
        let comp: NerkhBimishoDetailComponent;
        let fixture: ComponentFixture<NerkhBimishoDetailComponent>;
        const route = ({ data: of({ nerkh: new NerkhBimisho(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [NerkhBimishoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NerkhBimishoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NerkhBimishoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nerkh).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
