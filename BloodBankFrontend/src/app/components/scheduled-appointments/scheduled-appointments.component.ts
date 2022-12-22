import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Params } from '@angular/router';
import { ScheduledAppointment } from 'src/app/model/scheduled-appointment.model';
import { AppointmentService } from 'src/app/service/appointment.service';

@Component({
  selector: 'app-scheduled-appointments',
  templateUrl: './scheduled-appointments.component.html',
  styleUrls: ['./scheduled-appointments.component.css']
})
export class ScheduledAppointmentsComponent implements OnInit {
  title = "Your scheduled appointments";
  public dataSource = new MatTableDataSource<ScheduledAppointment>();
  public displayedColumns = ['date', 'time', 'duration', 'schedule'];
  public scheduledAppointments: ScheduledAppointment[] = [];

  constructor(private route: ActivatedRoute, private appointmentService: AppointmentService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      console.log(params['id']);
      this.appointmentService.findScheduledAppointmentsForRegisteredUser(params['id']).subscribe(res => {
    
         this.scheduledAppointments = res; 
         console.log(this.scheduledAppointments); 
         this.dataSource.data = this.scheduledAppointments;
       })
     });

  }
  cancelScheduledAppointment(id: number): void {

  }

}
