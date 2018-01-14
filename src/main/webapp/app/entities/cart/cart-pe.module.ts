import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeteasySharedModule } from '../../shared';
import {
    CartPeService,
    CartPePopupService,
    CartPeComponent,
    CartPeDetailComponent,
    CartPeDialogComponent,
    CartPePopupComponent,
    CartPeDeletePopupComponent,
    CartPeDeleteDialogComponent,
    cartRoute,
    cartPopupRoute,
} from './';

const ENTITY_STATES = [
    ...cartRoute,
    ...cartPopupRoute,
];

@NgModule({
    imports: [
        PeteasySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CartPeComponent,
        CartPeDetailComponent,
        CartPeDialogComponent,
        CartPeDeleteDialogComponent,
        CartPePopupComponent,
        CartPeDeletePopupComponent,
    ],
    entryComponents: [
        CartPeComponent,
        CartPeDialogComponent,
        CartPePopupComponent,
        CartPeDeleteDialogComponent,
        CartPeDeletePopupComponent,
    ],
    providers: [
        CartPeService,
        CartPePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyCartPeModule {}
