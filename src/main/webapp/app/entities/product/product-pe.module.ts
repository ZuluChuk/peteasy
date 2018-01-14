import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeteasySharedModule } from '../../shared';
import {
    ProductPeService,
    ProductPePopupService,
    ProductPeComponent,
    ProductPeDetailComponent,
    ProductPeDialogComponent,
    ProductPePopupComponent,
    ProductPeDeletePopupComponent,
    ProductPeDeleteDialogComponent,
    productRoute,
    productPopupRoute,
    ProductPeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...productRoute,
    ...productPopupRoute,
];

@NgModule({
    imports: [
        PeteasySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProductPeComponent,
        ProductPeDetailComponent,
        ProductPeDialogComponent,
        ProductPeDeleteDialogComponent,
        ProductPePopupComponent,
        ProductPeDeletePopupComponent,
    ],
    entryComponents: [
        ProductPeComponent,
        ProductPeDialogComponent,
        ProductPePopupComponent,
        ProductPeDeleteDialogComponent,
        ProductPeDeletePopupComponent,
    ],
    providers: [
        ProductPeService,
        ProductPePopupService,
        ProductPeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyProductPeModule {}
