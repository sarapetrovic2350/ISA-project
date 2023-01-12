export class Blood {
    id: string = ''; 
    quantaty: number = 0; 
    bloodType: string = ''; 

    public constructor(obj?: any) {
        if (obj) {
            this.id = obj.bloodId; 
            this.quantaty = obj.quantaty; 
            this.bloodType = obj.bloodType; 
        }
    }
}
