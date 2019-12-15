/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InsurancestartTestModule } from '../../../test.module';
import { SabegheKhesaratComponent } from 'app/entities/sabeghe-khesarat/sabeghe-khesarat.component';
import { SabegheKhesaratService } from 'app/entities/sabeghe-khesarat/sabeghe-khesarat.service';
import { SabegheKhesarat } from 'app/shared/model/sabeghe-khesarat.model';

describe('Component Tests', () => {
    describe('SabegheKhesarat Management Component', () => {
        let comp: SabegheKhesaratComponent;
        let fixture: ComponentFixture<SabegheKhesaratComponent>;
        let service: SabegheKhesaratService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [SabegheKhesaratComponent],
                providers: []
            })
                .overrideTemplate(SabegheKhesaratComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SabegheKhesaratComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SabegheKhesaratService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SabegheKhesarat(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sabegheKhesarats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
