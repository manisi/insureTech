/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsurancestartTestModule } from '../../../test.module';
import { NoeSabegheDetailComponent } from 'app/entities/noe-sabeghe/noe-sabeghe-detail.component';
import { NoeSabeghe } from 'app/shared/model/noe-sabeghe.model';

describe('Component Tests', () => {
    describe('NoeSabeghe Management Detail Component', () => {
        let comp: NoeSabegheDetailComponent;
        let fixture: ComponentFixture<NoeSabegheDetailComponent>;
        const route = ({ data: of({ noeSabeghe: new NoeSabeghe(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [NoeSabegheDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NoeSabegheDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NoeSabegheDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.noeSabeghe).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
