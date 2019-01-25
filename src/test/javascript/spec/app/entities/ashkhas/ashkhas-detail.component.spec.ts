/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AshkhasDetailComponent } from 'app/entities/ashkhas/ashkhas-detail.component';
import { Ashkhas } from 'app/shared/model/ashkhas.model';

describe('Component Tests', () => {
    describe('Ashkhas Management Detail Component', () => {
        let comp: AshkhasDetailComponent;
        let fixture: ComponentFixture<AshkhasDetailComponent>;
        const route = ({ data: of({ ashkhas: new Ashkhas(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AshkhasDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AshkhasDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AshkhasDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ashkhas).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
