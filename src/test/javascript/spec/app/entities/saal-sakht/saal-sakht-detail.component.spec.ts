/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SaalSakhtDetailComponent } from 'app/entities/saal-sakht/saal-sakht-detail.component';
import { SaalSakht } from 'app/shared/model/saal-sakht.model';

describe('Component Tests', () => {
    describe('SaalSakht Management Detail Component', () => {
        let comp: SaalSakhtDetailComponent;
        let fixture: ComponentFixture<SaalSakhtDetailComponent>;
        const route = ({ data: of({ saalSakht: new SaalSakht(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SaalSakhtDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SaalSakhtDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SaalSakhtDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.saalSakht).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
