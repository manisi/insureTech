/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SherkatBimeBimishoDetailComponent } from 'app/entities/sherkat-bime-bimisho/sherkat-bime-bimisho-detail.component';
import { SherkatBimeBimisho } from 'app/shared/model/sherkat-bime-bimisho.model';

describe('Component Tests', () => {
    describe('SherkatBimeBimisho Management Detail Component', () => {
        let comp: SherkatBimeBimishoDetailComponent;
        let fixture: ComponentFixture<SherkatBimeBimishoDetailComponent>;
        const route = ({ data: of({ sherkatBime: new SherkatBimeBimisho(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SherkatBimeBimishoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SherkatBimeBimishoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SherkatBimeBimishoDetailComponent);
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
