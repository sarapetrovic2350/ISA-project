import { Address } from "./address.model";

export class SystemAdministrator {
    constructor(
        public email: string = '',
        public password: string = '',
        public name: string = '',
        public surname: string = '',  
        public phoneNumber: string = '',
        public address: Address = new Address(),
        public jmbg: number = 0,
        public gender: string = '',
        public occupation: string = '',
        public occupationInfo: string= ''
      ) {}
}
