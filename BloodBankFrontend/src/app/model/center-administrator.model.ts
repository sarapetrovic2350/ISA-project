import { Address } from "./address.model";
import { medicalCenter } from "./medicalCenter.model";

export class CenterAdministrator {
    constructor(
        public email: string = '',
        public password: string = '10',
        public name: string = '',
        public surname: string = '',  
        public phoneNumber: string = '',
        public address: Address = new Address(),
        public jmbg: number = 0,
        public gender: string = '',
        public occupation: string = '',
        public occupationInfo: string= '',
        public medicalCenter: number = 0
      ) {}
}
