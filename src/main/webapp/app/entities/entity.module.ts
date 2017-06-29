import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { eetharDCaseModule } from './d-case/d-case.module';
import { eetharNGOModule } from './ngo/ngo.module';
import { eetharDonationModule } from './donation/donation.module';
/* needle-add-entity-module-import - add entity modules imports here */

@NgModule({
    imports: [
	eetharDCaseModule,	eetharNGOModule,	eetharDonationModule,
        /* needle-add-entity-module - add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class eetharEntityModule {}
