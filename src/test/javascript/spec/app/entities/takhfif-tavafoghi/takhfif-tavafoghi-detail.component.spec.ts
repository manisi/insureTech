/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { TakhfifTavafoghiDetailComponent } from 'app/entities/takhfif-tavafoghi/takhfif-tavafoghi-detail.component';
import { TakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';

describe('Component Tests', () => {
    describe('TakhfifTavafoghi Management Detail Component', () => {
        let comp: TakhfifTavafoghiDetailComponent;
        let fixture: ComponentFixture<TakhfifTavafoghiDetailComponent>;
        const route = ({ data: of({ takhfifTavafoghi: new TakhfifTavafoghi(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [TakhfifTavafoghiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TakhfifTavafoghiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TakhfifTavafoghiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.takhfifTavafoghi).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
