/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { OnvanKhodroDetailComponent } from 'app/entities/onvan-khodro/onvan-khodro-detail.component';
import { OnvanKhodro } from 'app/shared/model/onvan-khodro.model';

describe('Component Tests', () => {
    describe('OnvanKhodro Management Detail Component', () => {
        let comp: OnvanKhodroDetailComponent;
        let fixture: ComponentFixture<OnvanKhodroDetailComponent>;
        const route = ({ data: of({ onvanKhodro: new OnvanKhodro(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [OnvanKhodroDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OnvanKhodroDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OnvanKhodroDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.onvanKhodro).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
