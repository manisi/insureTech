/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratSarneshinDetailComponent } from 'app/entities/adam-khesarat-sarneshin/adam-khesarat-sarneshin-detail.component';
import { AdamKhesaratSarneshin } from 'app/shared/model/adam-khesarat-sarneshin.model';

describe('Component Tests', () => {
    describe('AdamKhesaratSarneshin Management Detail Component', () => {
        let comp: AdamKhesaratSarneshinDetailComponent;
        let fixture: ComponentFixture<AdamKhesaratSarneshinDetailComponent>;
        const route = ({ data: of({ adamKhesaratSarneshin: new AdamKhesaratSarneshin(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratSarneshinDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AdamKhesaratSarneshinDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdamKhesaratSarneshinDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.adamKhesaratSarneshin).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
