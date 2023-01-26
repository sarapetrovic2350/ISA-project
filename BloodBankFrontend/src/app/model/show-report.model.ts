export class ShowReport {
    userId: number = 0; 
    patientName: string = '';
    patientSurname: string = '';
    quantaty: number = 0; 
    blood: string = ''; 
    date: string=''; 

    public constructor(obj?: any) {
        if (obj) {
            this.userId = obj.user; 
            this.patientName= obj.patientName;
            this.patientSurname= obj.patientSurname;
            this.quantaty= obj.quantaty; 
            this.blood= obj.blood; 
            this.date= obj.date;  
        }
    }
}
