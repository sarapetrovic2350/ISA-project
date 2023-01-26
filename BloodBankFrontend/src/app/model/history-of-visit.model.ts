export class HistoryOfVisit {
    constructor(
        public reportId: number = 0,
        public date: string = '',
        public bloodType: string = '',
        public donatedBloodQuantity: string = '',  
        public medicalCenterName: string = ''
      ) {}
}