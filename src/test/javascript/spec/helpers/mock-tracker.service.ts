import { SpyObject } from './spyobject';
import { InsutechTrackerService } from 'app/core/tracker/tracker.service';

export class MockTrackerService extends SpyObject {
    constructor() {
        super(InsutechTrackerService);
    }

    connect() {}
}
