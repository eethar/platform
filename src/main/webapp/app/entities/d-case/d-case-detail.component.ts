import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { DCase } from './d-case.model';
import { DCaseService } from './d-case.service';

@Component({
    selector: 'jhi-d-case-detail',
    templateUrl: './d-case-detail.component.html'
})
export class DCaseDetailComponent implements OnInit, OnDestroy {

    DCase: DCase;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private DCaseService: DCaseService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['DCase']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDCases();
    }

    load(id) {
        this.DCaseService.find(id).subscribe((DCase) => {
            this.DCase = DCase;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDCases() {
        this.eventSubscriber = this.eventManager.subscribe('DCaseListModification', (response) => this.load(this.DCase.id));
    }
}
