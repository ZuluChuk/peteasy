import { BaseEntity } from './../../shared';

export class OrderEntryPe implements BaseEntity {
    constructor(
        public id?: number,
        public basePrice?: number,
        public totalPrice?: number,
        public amont?: number,
        public no?: number,
        public productId?: number,
        public orderId?: number,
    ) {
    }
}
