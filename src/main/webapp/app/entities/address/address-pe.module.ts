import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeteasySharedModule } from '../../shared';
import {
    AddressPeService,
    AddressPePopupService,
    AddressPeComponent,
    AddressPeDetailComponent,
    AddressPeDialogComponent,
    AddressPePopupComponent,
    AddressPeDeletePopupComponent,
    AddressPeDeleteDialogComponent,
    addressRoute,
    addressPopupRoute,
} from './';

const ENTITY_STATES = [
    ...addressRoute,
    ...addressPopupRoute,
];

@NgModule({
    imports: [
        PeteasySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AddressPeComponent,
        AddressPeDetailComponent,
        AddressPeDialogComponent,
        AddressPeDeleteDialogComponent,
        AddressPePopupComponent,
        AddressPeDeletePopupComponent,
    ],
    entryComponents: [
        AddressPeComponent,
        AddressPeDialogComponent,
        AddressPePopupComponent,
        AddressPeDeleteDialogComponent,
        AddressPeDeletePopupComponent,
    ],
    providers: [
        AddressPeService,
        AddressPePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyAddressPeModule {}
