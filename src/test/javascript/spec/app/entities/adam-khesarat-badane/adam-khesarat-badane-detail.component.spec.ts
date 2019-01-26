/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AdamKhesaratBadaneDetailComponent } from 'app/entities/adam-khesarat-badane/adam-khesarat-badane-detail.component';
import { AdamKhesaratBadane } from 'app/shared/model/adam-khesarat-badane.model';

describe('Component Tests', () => {
    describe('AdamKhesaratBadane Management Detail Component', () => {
        let comp: AdamKhesaratBadaneDetailComponent;
        let fixture: ComponentFixture<AdamKhesaratBadaneDetailComponent>;
        const route = ({ data: of({ adamKhesaratBadane: new AdamKhesaratBadane(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AdamKhesaratBadaneDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AdamKhesaratBadaneDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdamKhesaratBadaneDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.adamKhesaratBadane).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
