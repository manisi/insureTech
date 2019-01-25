/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SherkatBimeDetailComponent } from 'app/entities/sherkat-bime/sherkat-bime-detail.component';
import { SherkatBime } from 'app/shared/model/sherkat-bime.model';

describe('Component Tests', () => {
    describe('SherkatBime Management Detail Component', () => {
        let comp: SherkatBimeDetailComponent;
        let fixture: ComponentFixture<SherkatBimeDetailComponent>;
        const route = ({ data: of({ sherkatBime: new SherkatBime(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SherkatBimeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SherkatBimeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SherkatBimeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sherkatBime).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
