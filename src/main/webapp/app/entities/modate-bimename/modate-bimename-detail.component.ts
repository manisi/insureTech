import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModateBimename } from 'app/shared/model/modate-bimename.model';

@Component({
    selector: 'jhi-modate-bimename-detail',
    templateUrl: './modate-bimename-detail.component.html'
})
export class ModateBimenameDetailComponent implements OnInit {
    modateBimename: IModateBimename;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ modateBimename }) => {
            this.modateBimename = modateBimename;
        });
    }

    previousState() {
        window.history.back();
    }
}
