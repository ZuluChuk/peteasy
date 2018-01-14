import { BaseEntity } from './../../shared';

export class CartPe implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public abstractOrderId?: number,
        public customerId?: number,
    ) {
    }
}
