/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SabegheDetailComponent } from 'app/entities/sabeghe/sabeghe-detail.component';
import { Sabeghe } from 'app/shared/model/sabeghe.model';

describe('Component Tests', () => {
    describe('Sabeghe Management Detail Component', () => {
        let comp: SabegheDetailComponent;
        let fixture: ComponentFixture<SabegheDetailComponent>;
        const route = ({ data: of({ sabeghe: new Sabeghe(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SabegheDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SabegheDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SabegheDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sabeghe).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
