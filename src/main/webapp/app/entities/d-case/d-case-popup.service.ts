import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { DCase } from './d-case.model';
import { DCaseService } from './d-case.service';
@Injectable()
export class DCasePopupService {
    private isOpen = false;
    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private DCaseService: DCaseService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.DCaseService.find(id).subscribe((DCase) => {
                DCase.startDate = this.datePipe
                    .transform(DCase.startDate, 'yyyy-MM-ddThh:mm');
                this.DCaseModalRef(component, DCase);
            });
        } else {
            return this.DCaseModalRef(component, new DCase());
        }
    }

    DCaseModalRef(component: Component, DCase: DCase): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.DCase = DCase;
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
