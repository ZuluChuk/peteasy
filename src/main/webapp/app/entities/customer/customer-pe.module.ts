import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeteasySharedModule } from '../../shared';
import {
    CustomerPeService,
    CustomerPePopupService,
    CustomerPeComponent,
    CustomerPeDetailComponent,
    CustomerPeDialogComponent,
    CustomerPePopupComponent,
    CustomerPeDeletePopupComponent,
    CustomerPeDeleteDialogComponent,
    customerRoute,
    customerPopupRoute,
    CustomerPeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...customerRoute,
    ...customerPopupRoute,
];

@NgModule({
    imports: [
        PeteasySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CustomerPeComponent,
        CustomerPeDetailComponent,
        CustomerPeDialogComponent,
        CustomerPeDeleteDialogComponent,
        CustomerPePopupComponent,
        CustomerPeDeletePopupComponent,
    ],
    entryComponents: [
        CustomerPeComponent,
        CustomerPeDialogComponent,
        CustomerPePopupComponent,
        CustomerPeDeleteDialogComponent,
        CustomerPeDeletePopupComponent,
    ],
    providers: [
        CustomerPeService,
        CustomerPePopupService,
        CustomerPeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyCustomerPeModule {}
