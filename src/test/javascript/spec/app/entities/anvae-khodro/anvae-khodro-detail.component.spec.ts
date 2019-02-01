/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AnvaeKhodroDetailComponent } from 'app/entities/anvae-khodro/anvae-khodro-detail.component';
import { AnvaeKhodro } from 'app/shared/model/anvae-khodro.model';

describe('Component Tests', () => {
    describe('AnvaeKhodro Management Detail Component', () => {
        let comp: AnvaeKhodroDetailComponent;
        let fixture: ComponentFixture<AnvaeKhodroDetailComponent>;
        const route = ({ data: of({ anvaeKhodro: new AnvaeKhodro(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AnvaeKhodroDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AnvaeKhodroDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AnvaeKhodroDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.anvaeKhodro).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
