import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { eetharSharedModule } from '../../shared';

import {
    DonationService,
    DonationPopupService,
    DonationComponent,
    DonationDetailComponent,
    DonationDialogComponent,
    DonationPopupComponent,
    DonationDeletePopupComponent,
    DonationDeleteDialogComponent,
    donationRoute,
    donationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...donationRoute,
    ...donationPopupRoute,
];

@NgModule({
    imports: [
        eetharSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DonationComponent,
        DonationDetailComponent,
        DonationDialogComponent,
        DonationDeleteDialogComponent,
        DonationPopupComponent,
        DonationDeletePopupComponent,
    ],
    entryComponents: [
        DonationComponent,
        DonationDialogComponent,
        DonationPopupComponent,
        DonationDeleteDialogComponent,
        DonationDeletePopupComponent,
    ],
    providers: [
        DonationService,
        DonationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class eetharDonationModule {}
