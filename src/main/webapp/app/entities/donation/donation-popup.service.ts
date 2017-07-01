import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Donation } from './donation.model';
import { DonationService } from './donation.service';
@Injectable()
export class DonationPopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private donationService: DonationService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.donationService.find(id).subscribe((donation) => {
                donation.donationDate = this.datePipe
                    .transform(donation.donationDate, 'yyyy-MM-ddThh:mm');
                this.donationModalRef(component, donation);
            });
        } else {
            return this.donationModalRef(component, new Donation());
        }
    }

    donationModalRef(component: Component, donation: Donation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.donation = donation;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
