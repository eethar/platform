import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { eetharSharedModule } from '../../shared';

import {
    DCaseService,
    DCasePopupService,
    DCaseComponent,
    DCaseDetailComponent,
    DCaseDialogComponent,
    DCasePopupComponent,
    DCaseDeletePopupComponent,
    DCaseDeleteDialogComponent,
    DCaseRoute,
    DCasePopupRoute,
} from './';

const ENTITY_STATES = [
    ...DCaseRoute,
    ...DCasePopupRoute,
];

@NgModule({
    imports: [
        eetharSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DCaseComponent,
        DCaseDetailComponent,
        DCaseDialogComponent,
        DCaseDeleteDialogComponent,
        DCasePopupComponent,
        DCaseDeletePopupComponent,
    ],
    entryComponents: [
        DCaseComponent,
        DCaseDialogComponent,
        DCasePopupComponent,
        DCaseDeleteDialogComponent,
        DCaseDeletePopupComponent,
    ],
    providers: [
        DCaseService,
        DCasePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class eetharDCaseModule {}
