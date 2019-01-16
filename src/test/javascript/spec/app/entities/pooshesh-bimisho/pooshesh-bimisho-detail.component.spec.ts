/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { PoosheshBimishoDetailComponent } from 'app/entities/pooshesh-bimisho/pooshesh-bimisho-detail.component';
import { PoosheshBimisho } from 'app/shared/model/pooshesh-bimisho.model';

describe('Component Tests', () => {
    describe('PoosheshBimisho Management Detail Component', () => {
        let comp: PoosheshBimishoDetailComponent;
        let fixture: ComponentFixture<PoosheshBimishoDetailComponent>;
        const route = ({ data: of({ pooshesh: new PoosheshBimisho(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [PoosheshBimishoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PoosheshBimishoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PoosheshBimishoDetailComponent);
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
