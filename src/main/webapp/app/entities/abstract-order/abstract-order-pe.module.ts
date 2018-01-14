import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeteasySharedModule } from '../../shared';
import {
    AbstractOrderPeService,
    AbstractOrderPePopupService,
    AbstractOrderPeComponent,
    AbstractOrderPeDetailComponent,
    AbstractOrderPeDialogComponent,
    AbstractOrderPePopupComponent,
    AbstractOrderPeDeletePopupComponent,
    AbstractOrderPeDeleteDialogComponent,
    abstractOrderRoute,
    abstractOrderPopupRoute,
} from './';

const ENTITY_STATES = [
    ...abstractOrderRoute,
    ...abstractOrderPopupRoute,
];

@NgModule({
    imports: [
        PeteasySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AbstractOrderPeComponent,
        AbstractOrderPeDetailComponent,
        AbstractOrderPeDialogComponent,
        AbstractOrderPeDeleteDialogComponent,
        AbstractOrderPePopupComponent,
        AbstractOrderPeDeletePopupComponent,
    ],
    entryComponents: [
        AbstractOrderPeComponent,
        AbstractOrderPeDialogComponent,
        AbstractOrderPePopupComponent,
        AbstractOrderPeDeleteDialogComponent,
        AbstractOrderPeDeletePopupComponent,
    ],
    providers: [
        AbstractOrderPeService,
        AbstractOrderPePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyAbstractOrderPeModule {}
