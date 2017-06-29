export class Donation {
    constructor(
        public id?: number,
        public donerName?: string,
        public donerEmail?: string,
        public donationAmount?: number,
        public tipAmount?: number,
        public donationDate?: any,
    ) {
    }
}
