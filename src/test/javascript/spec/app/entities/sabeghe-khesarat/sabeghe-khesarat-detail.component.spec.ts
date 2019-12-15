/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { SabegheKhesaratDetailComponent } from 'app/entities/sabeghe-khesarat/sabeghe-khesarat-detail.component';
import { SabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';

describe('Component Tests', () => {
    describe('SabegheKhesarat Management Detail Component', () => {
        let comp: SabegheKhesaratDetailComponent;
        let fixture: ComponentFixture<SabegheKhesaratDetailComponent>;
        const route = ({ data: of({ sabegheKhesarat: new SabegheKhesarat(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SabegheKhesaratDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SabegheKhesaratDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SabegheKhesaratDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sabegheKhesarat).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
