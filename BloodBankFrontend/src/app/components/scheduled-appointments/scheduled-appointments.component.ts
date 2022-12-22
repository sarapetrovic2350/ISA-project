import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Params, Route, Router } from '@angular/router';
import { ScheduledAppointment } from 'src/app/model/scheduled-appointment.model';
import { AppointmentService } from 'src/app/service/appointment.service';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

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
  public registeredUserId: number = 0;

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService, private appointmentService: AppointmentService) { }

  ngOnInit(): void {
    this.registeredUserId = parseInt(this.authService.getCurrentUser().userId);
    this.appointmentService.findScheduledAppointmentsForRegisteredUser(this.registeredUserId).subscribe(res => {

      this.scheduledAppointments = res;
      console.log(this.scheduledAppointments);
      this.dataSource.data = this.scheduledAppointments;
    });
  }

  cancelScheduledAppointment(id: number): void {
    this.appointmentService.cancelScheduledAppointment(id).subscribe({
      next: (res) => {
        window.location.href = '/scheduled-appointments';
        Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: 'Appointment successfully cancelled!',
        })
      },
      error: (e) => {
        console.log(e);
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: e.error,
        })
      }
    });
  }

}
