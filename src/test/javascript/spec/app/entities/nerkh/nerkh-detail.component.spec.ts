/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { NerkhDetailComponent } from 'app/entities/nerkh/nerkh-detail.component';
import { Nerkh } from 'app/shared/model/nerkh.model';

describe('Component Tests', () => {
    describe('Nerkh Management Detail Component', () => {
        let comp: NerkhDetailComponent;
        let fixture: ComponentFixture<NerkhDetailComponent>;
        const route = ({ data: of({ nerkh: new Nerkh(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [NerkhDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NerkhDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NerkhDetailComponent);
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
