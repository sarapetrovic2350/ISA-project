export class ChangePasswordDTO {
    constructor(
        public email:string='',
        public oldPassword: string='',
        public password: string = '',
        public passwordRepeated = ''
    ){}
}
