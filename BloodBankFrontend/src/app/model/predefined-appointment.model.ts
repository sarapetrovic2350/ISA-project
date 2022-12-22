export class PredefinedAppointment {
    constructor(
        public duration: string = '',
        public date: string = '',
        public time: string = '',
        public administratorCenterID: string = '',
        public registeredUserID: string = '',
        public medicalCenterID: string = ''
      ) {}
}
