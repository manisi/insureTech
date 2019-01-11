import { Component, OnInit } from '@angular/core';
import { NgbTabsetConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'jhi-hi-there',
    templateUrl: './hi-there.component.html',
    styleUrls: ['hi-there.component.scss']
})
export class HiThereComponent implements OnInit {
    message: string;

    constructor(config: NgbTabsetConfig) {
        this.message = 'HiThereComponent message';
        config.justify = 'justified';
        // fill,start,end,center
        config.orientation = 'horizontal';
        config.type = 'pills';
    }

    ngOnInit() {}
}
