/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KohnegiBadaneDetailComponent } from 'app/entities/kohnegi-badane/kohnegi-badane-detail.component';
import { KohnegiBadane } from 'app/shared/model/kohnegi-badane.model';

describe('Component Tests', () => {
    describe('KohnegiBadane Management Detail Component', () => {
        let comp: KohnegiBadaneDetailComponent;
        let fixture: ComponentFixture<KohnegiBadaneDetailComponent>;
        const route = ({ data: of({ kohnegiBadane: new KohnegiBadane(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KohnegiBadaneDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KohnegiBadaneDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KohnegiBadaneDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.kohnegiBadane).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
