import { BaseEntity } from './../../shared';

export class OrderPe implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public abstractOrderId?: number,
        public customerId?: number,
    ) {
    }
}
