import { NGO } from '../ngo';
import { Donation } from '../donation';
export class DCase {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public startDate?: any,
        public NGO?: NGO,
        public donations?: Donation,
    ) {
    }
}
