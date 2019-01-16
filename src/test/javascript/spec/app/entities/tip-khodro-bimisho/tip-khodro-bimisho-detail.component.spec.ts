/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { TipKhodroBimishoDetailComponent } from 'app/entities/tip-khodro-bimisho/tip-khodro-bimisho-detail.component';
import { TipKhodroBimisho } from 'app/shared/model/tip-khodro-bimisho.model';

describe('Component Tests', () => {
    describe('TipKhodroBimisho Management Detail Component', () => {
        let comp: TipKhodroBimishoDetailComponent;
        let fixture: ComponentFixture<TipKhodroBimishoDetailComponent>;
        const route = ({ data: of({ tipKhodro: new TipKhodroBimisho(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [TipKhodroBimishoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipKhodroBimishoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipKhodroBimishoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipKhodro).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
