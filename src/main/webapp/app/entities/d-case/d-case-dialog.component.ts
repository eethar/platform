import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { DCase } from './d-case.model';
import { DCasePopupService } from './d-case-popup.service';
import { DCaseService } from './d-case.service';
import { NGO, NGOService } from '../ngo';
import { Donation, DonationService } from '../donation';

@Component({
    selector: 'jhi-d-case-dialog',
    templateUrl: './d-case-dialog.component.html'
})
export class DCaseDialogComponent implements OnInit {

    DCase: DCase;
    authorities: any[];
    isSaving: boolean;

    ngoes: NGO[];

    donations: Donation[];
    
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private DCaseService: DCaseService,
        private NGOService: NGOService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['DCase']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.NGOService.query({filter: 'dcase-is-null'}).subscribe((res: Response) => {
            if (!this.DCase.NGO || !this.DCase.NGO.id) {
                this.ngoes = res.json();
            } else {
                this.NGOService.find(this.DCase.NGO.id).subscribe((subRes: NGO) => {
                    this.ngoes = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.NGOService.query({filter: 'dcase-is-null'}).subscribe((res: Response) => {
            if (!this.DCase.donations || !this.DCase.donations.id) {
                this.donations = res.json();
            } else {
                this.NGOService.find(this.DCase.donations.id).subscribe((subRes: Donation) => {
                    this.donations = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.DCase.id !== undefined) {
            this.DCaseService.update(this.DCase)
                .subscribe((res: DCase) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.DCaseService.create(this.DCase)
                .subscribe((res: DCase) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: DCase) {
        this.eventManager.broadcast({ name: 'DCaseListModification', content: 'OK'});
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

    trackNGOById(index: number, item: NGO) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-d-case-popup',
    template: ''
})
export class DCasePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private DCasePopupService: DCasePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.DCasePopupService
                    .open(DCaseDialogComponent, params['id']);
            } else {
                this.modalRef = this.DCasePopupService
                    .open(DCaseDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
