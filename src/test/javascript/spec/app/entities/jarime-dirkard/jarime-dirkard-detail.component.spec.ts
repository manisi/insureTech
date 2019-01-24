/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { JarimeDirkardDetailComponent } from 'app/entities/jarime-dirkard/jarime-dirkard-detail.component';
import { JarimeDirkard } from 'app/shared/model/jarime-dirkard.model';

describe('Component Tests', () => {
    describe('JarimeDirkard Management Detail Component', () => {
        let comp: JarimeDirkardDetailComponent;
        let fixture: ComponentFixture<JarimeDirkardDetailComponent>;
        const route = ({ data: of({ jarimeDirkard: new JarimeDirkard(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [JarimeDirkardDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(JarimeDirkardDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(JarimeDirkardDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.jarimeDirkard).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
