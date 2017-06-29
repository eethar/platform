import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { NGO } from './ngo.model';
import { NGOPopupService } from './ngo-popup.service';
import { NGOService } from './ngo.service';

@Component({
    selector: 'jhi-ngo-delete-dialog',
    templateUrl: './ngo-delete-dialog.component.html'
})
export class NGODeleteDialogComponent {

    NGO: NGO;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private NGOService: NGOService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['NGO']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.NGOService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'NGOListModification',
                content: 'Deleted an NGO'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ngo-delete-popup',
    template: ''
})
export class NGODeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private NGOPopupService: NGOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.NGOPopupService
                .open(NGODeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
