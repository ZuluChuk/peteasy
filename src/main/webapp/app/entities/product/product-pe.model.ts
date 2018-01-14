import { BaseEntity } from './../../shared';

export class ProductPe implements BaseEntity {
    constructor(
        public id?: number,
        public image?: string,
        public descriptionContentType?: string,
        public description?: any,
        public price?: number,
    ) {
    }
}
