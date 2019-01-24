/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratDetailComponent } from 'app/entities/adam-khesarat/adam-khesarat-detail.component';
import { AdamKhesarat } from 'app/shared/model/adam-khesarat.model';

describe('Component Tests', () => {
    describe('AdamKhesarat Management Detail Component', () => {
        let comp: AdamKhesaratDetailComponent;
        let fixture: ComponentFixture<AdamKhesaratDetailComponent>;
        const route = ({ data: of({ adamKhesarat: new AdamKhesarat(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AdamKhesaratDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdamKhesaratDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.adamKhesarat).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
