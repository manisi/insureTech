import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITipKhodro } from 'app/shared/model/tip-khodro.model';
import { TipKhodroService } from './tip-khodro.service';
import { IKhodro } from 'app/shared/model/khodro.model';
import { KhodroService } from 'app/entities/khodro';

@Component({
    selector: 'insutech-tip-khodro-update',
    templateUrl: './tip-khodro-update.component.html'
})
export class TipKhodroUpdateComponent implements OnInit {
    tipKhodro: ITipKhodro;
    isSaving: boolean;

    khodros: IKhodro[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected tipKhodroService: TipKhodroService,
        protected khodroService: KhodroService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipKhodro }) => {
            this.tipKhodro = tipKhodro;
        });
        this.khodroService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IKhodro[]>) => mayBeOk.ok),
                map((response: HttpResponse<IKhodro[]>) => response.body)
            )
            .subscribe((res: IKhodro[]) => (this.khodros = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipKhodro.id !== undefined) {
            this.subscribeToSaveResponse(this.tipKhodroService.update(this.tipKhodro));
        } else {
            this.subscribeToSaveResponse(this.tipKhodroService.create(this.tipKhodro));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipKhodro>>) {
        result.subscribe((res: HttpResponse<ITipKhodro>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackKhodroById(index: number, item: IKhodro) {
        return item.id;
    }
}
