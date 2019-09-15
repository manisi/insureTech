/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { GroupOperationsDataDetailComponent } from 'app/entities/group-operations-data/group-operations-data-detail.component';
import { GroupOperationsData } from 'app/shared/model/group-operations-data.model';

describe('Component Tests', () => {
    describe('GroupOperationsData Management Detail Component', () => {
        let comp: GroupOperationsDataDetailComponent;
        let fixture: ComponentFixture<GroupOperationsDataDetailComponent>;
        const route = ({ data: of({ groupOperationsData: new GroupOperationsData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [GroupOperationsDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GroupOperationsDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GroupOperationsDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.groupOperationsData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
