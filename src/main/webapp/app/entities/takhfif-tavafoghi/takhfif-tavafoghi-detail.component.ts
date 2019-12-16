import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITakhfifTavafoghi } from 'app/shared/model/takhfif-tavafoghi.model';

@Component({
    selector: 'jhi-takhfif-tavafoghi-detail',
    templateUrl: './takhfif-tavafoghi-detail.component.html'
})
export class TakhfifTavafoghiDetailComponent implements OnInit {
    takhfifTavafoghi: ITakhfifTavafoghi;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ takhfifTavafoghi }) => {
            this.takhfifTavafoghi = takhfifTavafoghi;
        });
    }

    previousState() {
        window.history.back();
    }
}
