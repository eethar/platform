import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { NGO } from './ngo.model';
import { NGOService } from './ngo.service';

@Component({
    selector: 'jhi-ngo-detail',
    templateUrl: './ngo-detail.component.html'
})
export class NGODetailComponent implements OnInit, OnDestroy {

    NGO: NGO;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private NGOService: NGOService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['NGO']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNGOes();
    }

    load(id) {
        this.NGOService.find(id).subscribe((NGO) => {
            this.NGO = NGO;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInNGOes() {
        this.eventSubscriber = this.eventManager.subscribe('NGOListModification', (response) => this.load(this.NGO.id));
    }
}
