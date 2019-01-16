/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhodroBimishoDetailComponent } from 'app/entities/khodro-bimisho/khodro-bimisho-detail.component';
import { KhodroBimisho } from 'app/shared/model/khodro-bimisho.model';

describe('Component Tests', () => {
    describe('KhodroBimisho Management Detail Component', () => {
        let comp: KhodroBimishoDetailComponent;
        let fixture: ComponentFixture<KhodroBimishoDetailComponent>;
        const route = ({ data: of({ khodro: new KhodroBimisho(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhodroBimishoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KhodroBimishoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhodroBimishoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.khodro).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
