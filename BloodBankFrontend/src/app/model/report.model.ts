export class Report {
    constructor(
        public administratorId: string = '',
        public customerId: number = 0,
        public bloodId: number = 0,
        public aapointmentsId: number = 0,
        public haemoglobinValue: number = 0,
        public heart: string = '',  
        public lungs: string = '',
        public weight: number = 0,
        public height: number = 0,
        public bloodPresure: number = 0,
        public reportStatus: string = '',
        public quantaty: number = 0,
        public reason: string='', 
        public date: string=''
      ) {}
}
