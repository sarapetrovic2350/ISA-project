export class ScheduledAppointment {
    constructor(
        public appointmentId: number = 0,
        public registeredUserId: number = 0,
        public date: string = '',
        public time: string = '',
        public duration: string = '',
        public medicalCenterName: string = ''
      ) {}
}
