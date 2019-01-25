import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IJarimeDirkard } from 'app/shared/model/jarime-dirkard.model';
import { JarimeDirkardService } from './jarime-dirkard.service';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from 'app/entities/grouh-khodro';

@Component({
    selector: 'jhi-jarime-dirkard-update',
    templateUrl: './jarime-dirkard-update.component.html'
})
export class JarimeDirkardUpdateComponent implements OnInit {
    jarimeDirkard: IJarimeDirkard;
    isSaving: boolean;

    grouhkhodros: IGrouhKhodro[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected jarimeDirkardService: JarimeDirkardService,
        protected grouhKhodroService: GrouhKhodroService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jarimeDirkard }) => {
            this.jarimeDirkard = jarimeDirkard;
        });
        this.grouhKhodroService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IGrouhKhodro[]>) => mayBeOk.ok),
                map((response: HttpResponse<IGrouhKhodro[]>) => response.body)
            )
            .subscribe((res: IGrouhKhodro[]) => (this.grouhkhodros = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.jarimeDirkard.id !== undefined) {
            this.subscribeToSaveResponse(this.jarimeDirkardService.update(this.jarimeDirkard));
        } else {
            this.subscribeToSaveResponse(this.jarimeDirkardService.create(this.jarimeDirkard));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IJarimeDirkard>>) {
        result.subscribe((res: HttpResponse<IJarimeDirkard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackGrouhKhodroById(index: number, item: IGrouhKhodro) {
        return item.id;
    }
}
