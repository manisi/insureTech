/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { AshkhasBimishoDetailComponent } from 'app/entities/ashkhas-bimisho/ashkhas-bimisho-detail.component';
import { AshkhasBimisho } from 'app/shared/model/ashkhas-bimisho.model';

describe('Component Tests', () => {
    describe('AshkhasBimisho Management Detail Component', () => {
        let comp: AshkhasBimishoDetailComponent;
        let fixture: ComponentFixture<AshkhasBimishoDetailComponent>;
        const route = ({ data: of({ ashkhas: new AshkhasBimisho(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [AshkhasBimishoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AshkhasBimishoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AshkhasBimishoDetailComponent);
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
