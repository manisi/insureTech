/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { KhesaratSrneshinDetailComponent } from 'app/entities/khesarat-srneshin/khesarat-srneshin-detail.component';
import { KhesaratSrneshin } from 'app/shared/model/khesarat-srneshin.model';

describe('Component Tests', () => {
    describe('KhesaratSrneshin Management Detail Component', () => {
        let comp: KhesaratSrneshinDetailComponent;
        let fixture: ComponentFixture<KhesaratSrneshinDetailComponent>;
        const route = ({ data: of({ khesaratSrneshin: new KhesaratSrneshin(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [KhesaratSrneshinDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KhesaratSrneshinDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhesaratSrneshinDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.khesaratSrneshin).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
