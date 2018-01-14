import { BaseEntity } from './../../shared';

export class CountryPe implements BaseEntity {
    constructor(
        public id?: number,
        public isoCode?: string,
        public name?: string,
    ) {
    }
}
