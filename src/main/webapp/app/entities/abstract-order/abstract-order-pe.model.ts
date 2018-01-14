import { BaseEntity } from './../../shared';

export class AbstractOrderPe implements BaseEntity {
    constructor(
        public id?: number,
        public subTotal?: number,
        public total?: number,
        public tax?: number,
        public shippingCost?: number,
        public shippingAddressId?: number,
        public billingAddressId?: number,
        public entries?: BaseEntity[],
        public orderId?: number,
        public cartId?: number,
    ) {
    }
}
