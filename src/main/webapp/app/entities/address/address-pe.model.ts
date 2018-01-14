import { BaseEntity } from './../../shared';

export class AddressPe implements BaseEntity {
    constructor(
        public id?: number,
        public addressLine1?: string,
        public addressLine2?: string,
        public city?: string,
        public countryId?: number,
        public shippingCustomerId?: number,
        public billingCustomerId?: number,
    ) {
    }
}
