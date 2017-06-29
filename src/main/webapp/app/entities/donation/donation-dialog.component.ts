import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Donation } from './donation.model';
import { DonationPopupService } from './donation-popup.service';
import { DonationService } from './donation.service';

@Component({
    selector: 'jhi-donation-dialog',
    templateUrl: './donation-dialog.component.html'
})
export class DonationDialogComponent implements OnInit {

    donation: Donation;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private donationService: DonationService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['donation']);
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
        if (this.donation.id !== undefined) {
            this.donationService.update(this.donation)
                .subscribe((res: Donation) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.donationService.create(this.donation)
                .subscribe((res: Donation) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Donation) {
        this.eventManager.broadcast({ name: 'donationListModification', content: 'OK'});
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
    selector: 'jhi-donation-popup',
    template: ''
})
export class DonationPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private donationPopupService: DonationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.donationPopupService
                    .open(DonationDialogComponent, params['id']);
            } else {
                this.modalRef = this.donationPopupService
                    .open(DonationDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
