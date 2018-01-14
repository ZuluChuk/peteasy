import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeteasySharedModule } from '../../shared';
import {
    OrderEntryPeService,
    OrderEntryPePopupService,
    OrderEntryPeComponent,
    OrderEntryPeDetailComponent,
    OrderEntryPeDialogComponent,
    OrderEntryPePopupComponent,
    OrderEntryPeDeletePopupComponent,
    OrderEntryPeDeleteDialogComponent,
    orderEntryRoute,
    orderEntryPopupRoute,
    OrderEntryPeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...orderEntryRoute,
    ...orderEntryPopupRoute,
];

@NgModule({
    imports: [
        PeteasySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        OrderEntryPeComponent,
        OrderEntryPeDetailComponent,
        OrderEntryPeDialogComponent,
        OrderEntryPeDeleteDialogComponent,
        OrderEntryPePopupComponent,
        OrderEntryPeDeletePopupComponent,
    ],
    entryComponents: [
        OrderEntryPeComponent,
        OrderEntryPeDialogComponent,
        OrderEntryPePopupComponent,
        OrderEntryPeDeleteDialogComponent,
        OrderEntryPeDeletePopupComponent,
    ],
    providers: [
        OrderEntryPeService,
        OrderEntryPePopupService,
        OrderEntryPeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyOrderEntryPeModule {}
