/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { TipKhodroDetailComponent } from 'app/entities/tip-khodro/tip-khodro-detail.component';
import { TipKhodro } from 'app/shared/model/tip-khodro.model';

describe('Component Tests', () => {
    describe('TipKhodro Management Detail Component', () => {
        let comp: TipKhodroDetailComponent;
        let fixture: ComponentFixture<TipKhodroDetailComponent>;
        const route = ({ data: of({ tipKhodro: new TipKhodro(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [TipKhodroDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipKhodroDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipKhodroDetailComponent);
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
