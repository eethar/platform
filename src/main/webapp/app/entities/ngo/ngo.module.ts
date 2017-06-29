import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { eetharSharedModule } from '../../shared';

import {
    NGOService,
    NGOPopupService,
    NGOComponent,
    NGODetailComponent,
    NGODialogComponent,
    NGOPopupComponent,
    NGODeletePopupComponent,
    NGODeleteDialogComponent,
    NGORoute,
    NGOPopupRoute,
} from './';

const ENTITY_STATES = [
    ...NGORoute,
    ...NGOPopupRoute,
];

@NgModule({
    imports: [
        eetharSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        NGOComponent,
        NGODetailComponent,
        NGODialogComponent,
        NGODeleteDialogComponent,
        NGOPopupComponent,
        NGODeletePopupComponent,
    ],
    entryComponents: [
        NGOComponent,
        NGODialogComponent,
        NGOPopupComponent,
        NGODeleteDialogComponent,
        NGODeletePopupComponent,
    ],
    providers: [
        NGOService,
        NGOPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class eetharNGOModule {}
