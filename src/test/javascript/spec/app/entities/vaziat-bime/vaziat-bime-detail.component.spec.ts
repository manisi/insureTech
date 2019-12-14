/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { VaziatBimeDetailComponent } from 'app/entities/vaziat-bime/vaziat-bime-detail.component';
import { VaziatBime } from 'app/shared/model/vaziat-bime.model';

describe('Component Tests', () => {
    describe('VaziatBime Management Detail Component', () => {
        let comp: VaziatBimeDetailComponent;
        let fixture: ComponentFixture<VaziatBimeDetailComponent>;
        const route = ({ data: of({ vaziatBime: new VaziatBime(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [VaziatBimeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VaziatBimeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VaziatBimeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vaziatBime).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
