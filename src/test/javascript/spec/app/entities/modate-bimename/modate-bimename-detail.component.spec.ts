/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { ModateBimenameDetailComponent } from 'app/entities/modate-bimename/modate-bimename-detail.component';
import { ModateBimename } from 'app/shared/model/modate-bimename.model';

describe('Component Tests', () => {
    describe('ModateBimename Management Detail Component', () => {
        let comp: ModateBimenameDetailComponent;
        let fixture: ComponentFixture<ModateBimenameDetailComponent>;
        const route = ({ data: of({ modateBimename: new ModateBimename(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [ModateBimenameDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ModateBimenameDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ModateBimenameDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.modateBimename).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
