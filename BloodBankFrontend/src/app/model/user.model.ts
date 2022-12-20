import { Address } from "./address.model";

export class User {
    constructor(
        public userId: string = '',
        public email: string = '',
        public password: string = '',
        public passwordConfirm: string = '',
        public name: string = '',
        public surname: string = '',  
        public phoneNumber: string = '',
        public address: Address = new Address(),
        public jmbg: number = 0,
        public gender: string = '',
        public occupation: string = '',
        public occupationInfo: string= '',
        public userType: string=''
      ) {}
}

export class AuthRequest {
  constructor(public email: string = '', public password: string = '') {}
}

