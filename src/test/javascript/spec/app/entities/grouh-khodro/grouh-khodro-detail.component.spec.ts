/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { GrouhKhodroDetailComponent } from 'app/entities/grouh-khodro/grouh-khodro-detail.component';
import { GrouhKhodro } from 'app/shared/model/grouh-khodro.model';

describe('Component Tests', () => {
    describe('GrouhKhodro Management Detail Component', () => {
        let comp: GrouhKhodroDetailComponent;
        let fixture: ComponentFixture<GrouhKhodroDetailComponent>;
        const route = ({ data: of({ grouhKhodro: new GrouhKhodro(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [GrouhKhodroDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GrouhKhodroDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GrouhKhodroDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.grouhKhodro).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
