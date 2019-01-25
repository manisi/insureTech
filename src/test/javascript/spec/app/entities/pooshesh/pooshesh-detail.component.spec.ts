/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { PoosheshDetailComponent } from 'app/entities/pooshesh/pooshesh-detail.component';
import { Pooshesh } from 'app/shared/model/pooshesh.model';

describe('Component Tests', () => {
    describe('Pooshesh Management Detail Component', () => {
        let comp: PoosheshDetailComponent;
        let fixture: ComponentFixture<PoosheshDetailComponent>;
        const route = ({ data: of({ pooshesh: new Pooshesh(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [PoosheshDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PoosheshDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PoosheshDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pooshesh).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
