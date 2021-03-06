import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { NGO } from './ngo.model';
import { NGOPopupService } from './ngo-popup.service';
import { NGOService } from './ngo.service';

@Component({
    selector: 'jhi-ngo-dialog',
    templateUrl: './ngo-dialog.component.html'
})
export class NGODialogComponent implements OnInit {

    NGO: NGO;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private NGOService: NGOService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['NGO']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.NGO.id !== undefined) {
            this.NGOService.update(this.NGO)
                .subscribe((res: NGO) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.NGOService.create(this.NGO)
                .subscribe((res: NGO) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: NGO) {
        this.eventManager.broadcast({ name: 'NGOListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-ngo-popup',
    template: ''
})
export class NGOPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private NGOPopupService: NGOPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.NGOPopupService
                    .open(NGODialogComponent, params['id']);
            } else {
                this.modalRef = this.NGOPopupService
                    .open(NGODialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
