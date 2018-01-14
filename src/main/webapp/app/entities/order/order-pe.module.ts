import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeteasySharedModule } from '../../shared';
import {
    OrderPeService,
    OrderPePopupService,
    OrderPeComponent,
    OrderPeDetailComponent,
    OrderPeDialogComponent,
    OrderPePopupComponent,
    OrderPeDeletePopupComponent,
    OrderPeDeleteDialogComponent,
    orderRoute,
    orderPopupRoute,
    OrderPeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...orderRoute,
    ...orderPopupRoute,
];

@NgModule({
    imports: [
        PeteasySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        OrderPeComponent,
        OrderPeDetailComponent,
        OrderPeDialogComponent,
        OrderPeDeleteDialogComponent,
        OrderPePopupComponent,
        OrderPeDeletePopupComponent,
    ],
    entryComponents: [
        OrderPeComponent,
        OrderPeDialogComponent,
        OrderPePopupComponent,
        OrderPeDeleteDialogComponent,
        OrderPeDeletePopupComponent,
    ],
    providers: [
        OrderPeService,
        OrderPePopupService,
        OrderPeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyOrderPeModule {}
