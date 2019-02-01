import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAnvaeKhodro } from 'app/shared/model/anvae-khodro.model';
import { AnvaeKhodroService } from './anvae-khodro.service';
import { IGrouhKhodro } from 'app/shared/model/grouh-khodro.model';
import { GrouhKhodroService } from 'app/entities/grouh-khodro';

@Component({
    selector: 'jhi-anvae-khodro-update',
    templateUrl: './anvae-khodro-update.component.html'
})
export class AnvaeKhodroUpdateComponent implements OnInit {
    anvaeKhodro: IAnvaeKhodro;
    isSaving: boolean;

    grouhkhodros: IGrouhKhodro[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected anvaeKhodroService: AnvaeKhodroService,
        protected grouhKhodroService: GrouhKhodroService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ anvaeKhodro }) => {
            this.anvaeKhodro = anvaeKhodro;
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
        if (this.anvaeKhodro.id !== undefined) {
            this.subscribeToSaveResponse(this.anvaeKhodroService.update(this.anvaeKhodro));
        } else {
            this.subscribeToSaveResponse(this.anvaeKhodroService.create(this.anvaeKhodro));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnvaeKhodro>>) {
        result.subscribe((res: HttpResponse<IAnvaeKhodro>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
