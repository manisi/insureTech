/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { MohasebeBadaneDetailComponent } from 'app/entities/mohasebe-badane/mohasebe-badane-detail.component';
import { MohasebeBadane } from 'app/shared/model/mohasebe-badane.model';

describe('Component Tests', () => {
    describe('MohasebeBadane Management Detail Component', () => {
        let comp: MohasebeBadaneDetailComponent;
        let fixture: ComponentFixture<MohasebeBadaneDetailComponent>;
        const route = ({ data: of({ mohasebeBadane: new MohasebeBadane(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [MohasebeBadaneDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MohasebeBadaneDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MohasebeBadaneDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mohasebeBadane).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
