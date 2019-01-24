/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KohnegiDetailComponent } from 'app/entities/kohnegi/kohnegi-detail.component';
import { Kohnegi } from 'app/shared/model/kohnegi.model';

describe('Component Tests', () => {
    describe('Kohnegi Management Detail Component', () => {
        let comp: KohnegiDetailComponent;
        let fixture: ComponentFixture<KohnegiDetailComponent>;
        const route = ({ data: of({ kohnegi: new Kohnegi(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KohnegiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KohnegiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KohnegiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.kohnegi).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
