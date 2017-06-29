import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { DCase } from './d-case.model';
import { DCasePopupService } from './d-case-popup.service';
import { DCaseService } from './d-case.service';

@Component({
    selector: 'jhi-d-case-delete-dialog',
    templateUrl: './d-case-delete-dialog.component.html'
})
export class DCaseDeleteDialogComponent {

    DCase: DCase;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private DCaseService: DCaseService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['DCase']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.DCaseService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'DCaseListModification',
                content: 'Deleted an DCase'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-d-case-delete-popup',
    template: ''
})
export class DCaseDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private DCasePopupService: DCasePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.DCasePopupService
                .open(DCaseDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
