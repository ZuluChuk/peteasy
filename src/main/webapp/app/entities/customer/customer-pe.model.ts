import { BaseEntity } from './../../shared';

export class CustomerPe implements BaseEntity {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public phoneNumber?: string,
        public registrationDate?: any,
        public orders?: BaseEntity[],
        public carts?: BaseEntity[],
        public shippingAddresses?: BaseEntity[],
        public billingAddresses?: BaseEntity[],
    ) {
    }
}
