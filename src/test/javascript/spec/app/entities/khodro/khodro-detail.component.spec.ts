/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhodroDetailComponent } from 'app/entities/khodro/khodro-detail.component';
import { Khodro } from 'app/shared/model/khodro.model';

describe('Component Tests', () => {
    describe('Khodro Management Detail Component', () => {
        let comp: KhodroDetailComponent;
        let fixture: ComponentFixture<KhodroDetailComponent>;
        const route = ({ data: of({ khodro: new Khodro(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhodroDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KhodroDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhodroDetailComponent);
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
