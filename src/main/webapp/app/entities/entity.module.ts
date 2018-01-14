import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PeteasyProductPeModule } from './product/product-pe.module';
import { PeteasyAbstractOrderPeModule } from './abstract-order/abstract-order-pe.module';
import { PeteasyOrderPeModule } from './order/order-pe.module';
import { PeteasyCartPeModule } from './cart/cart-pe.module';
import { PeteasyOrderEntryPeModule } from './order-entry/order-entry-pe.module';
import { PeteasyCustomerPeModule } from './customer/customer-pe.module';
import { PeteasyAddressPeModule } from './address/address-pe.module';
import { PeteasyCountryPeModule } from './country/country-pe.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        PeteasyProductPeModule,
        PeteasyAbstractOrderPeModule,
        PeteasyOrderPeModule,
        PeteasyCartPeModule,
        PeteasyOrderEntryPeModule,
        PeteasyCustomerPeModule,
        PeteasyAddressPeModule,
        PeteasyCountryPeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PeteasyEntityModule {}
