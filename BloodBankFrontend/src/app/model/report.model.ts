export class Report {
    constructor(
        public administratorEmail: string = '',
        public customerId: number = 0,
        public bloodId: number = 0,
        public appointmentId: number = 0,
        public haemoglobinValue: number = 0,
        public heart: string = '',  
        public lungs: string = '',
        public weight: number = 0,
        public height: number = 0,
        public bloodPreasure: number = 0,
        public reportStatus: string = '',
        public quantaty: number = 0,
        public reason: string='', 
        public date: string='', 
        public equipmentQuantaty: number = 0, 
        public present: string='',
        public bloodType: string=''
      ) {}
}
