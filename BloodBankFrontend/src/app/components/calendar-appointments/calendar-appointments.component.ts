import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { EJ2Instance, EventSettingsModel } from '@syncfusion/ej2-angular-schedule';
import { AppointmetForCalendar } from 'src/app/model/appointmet-for-calendar';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { ShowAppointments } from 'src/app/model/show-appointments.model';
import { User } from 'src/app/model/user.model';
import { AppointmentService } from 'src/app/service/appointment.service';
import { AuthService } from 'src/app/service/auth.service';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service';

@Component({
  selector: 'app-calendar-appointments',
  //template: '<ejs-schedule><ejs-schedule>',
  templateUrl: './calendar-appointments.component.html',
  styleUrls: ['./calendar-appointments.component.css']
})
export class CalendarAppointmentsComponent implements OnInit {

  //public dataSource = new MatTableDataSource<ShowAppointments>();
  public appointments: ShowAppointments[] = [];
  public appointmentsForCalendar: AppointmetForCalendar[] = [];
  //public appointment = new AppointmetForCalendar();
  //public appointment: ShowAppointments | undefined = undefined;
  public user = new User();
  public administrator: CenterAdministrator | undefined = undefined;


  /* public eventObject: EventSettingsModel = {
    dataSource: [{
      StartTime: new Date(2023,7,26,4,0),
      EndTime: new Date(2023,7,26,5,0)
    },
    {
      StartTime: new Date(2023,7,26,6,0),
      EndTime: new Date(2023,7,26,7,0)
    }]
  } */

  public eventObject: EventSettingsModel = {
    dataSource: this.appointmentsForCalendar
  }

  constructor(private appointmentService: AppointmentService,private centerAdministratorService: CenterAdministratorService,  private Router: Router, private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit(): void {

    this.user = this.authService.getCurrentUser();
      this.centerAdministratorService.findByEmail(this.user.email).subscribe(res => {
        this.administrator = res;
        //console.log(this.administrator); 
        
          this.appointmentService.getAllAppointmentsByCenterAdministrator(this.administrator.email).subscribe(data => {
            this.appointments = data;
            this.onCreated()
            console.log(this.appointmentsForCalendar)
            

          })
      })
  }

  public onCreated() {
    for(let i = 0; i<this.appointments.length; i++){
      var appointment = new AppointmetForCalendar();
      appointment.Subject = this.appointments[i].userName.concat(" " + this.appointments[i].userSurname)
      
      const date = this.appointments[i].date
      const [year, month, day] = date.split('-')
      const time = this.appointments[i].time
      const [hour, minute] = time.split(':')             
      appointment.StartTime = new Date(+year, +month - 1, +day, +hour, +minute)

      appointment.EndTime = new Date(+year, +month - 1, +day, +hour, +minute + 20)
      
      //this.appointment.EndTime.setMinutes(this.appointment.EndTime.getMinutes() + 20)
      
      //console.log(this.appointment.StartTime)
      //console.log(this.appointment.EndTime)
      console.log(appointment)

      this.appointmentsForCalendar.push(appointment)
      //console.log(this.appointmentsForCalendar)    
    }
    //console.log(this.appointmentsForCalendar)
    //console.log(this.eventObject.dataSource)
    //this.dataSource.data = this.appointments;
  }

}
