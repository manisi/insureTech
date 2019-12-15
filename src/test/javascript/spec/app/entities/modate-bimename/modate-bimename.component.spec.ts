/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InsurancestartTestModule } from '../../../test.module';
import { ModateBimenameComponent } from 'app/entities/modate-bimename/modate-bimename.component';
import { ModateBimenameService } from 'app/entities/modate-bimename/modate-bimename.service';
import { ModateBimename } from 'app/shared/model/modate-bimename.model';

describe('Component Tests', () => {
    describe('ModateBimename Management Component', () => {
        let comp: ModateBimenameComponent;
        let fixture: ComponentFixture<ModateBimenameComponent>;
        let service: ModateBimenameService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InsurancestartTestModule],
                declarations: [ModateBimenameComponent],
                providers: []
            })
                .overrideTemplate(ModateBimenameComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ModateBimenameComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ModateBimenameService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ModateBimename(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.modateBimenames[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
