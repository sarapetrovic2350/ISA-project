import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Params } from '@angular/router';
import { AppointmentService } from 'src/app/service/appointment.service';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-predefined-appointments-for-center',
  templateUrl: './predefined-appointments-for-center.component.html',
  styleUrls: ['./predefined-appointments-for-center.component.css']
})
export class PredefinedAppointmentsForCenterComponent implements OnInit {

  title = "Predefined appointments";
  public dataSource = new MatTableDataSource<any>();
  public displayedColumns = ['date', 'time', 'duration', 'schedule'];
  public predefinedAppointments: any[] = [];
  constructor(private route: ActivatedRoute, private appointmentService: AppointmentService, private authService: AuthService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      console.log(params['id']);
      this.appointmentService.findPredefinedAppointmentsForMedicalCenter(params['id']).subscribe(res => {
    
         this.predefinedAppointments = res; 
         console.log(this.predefinedAppointments); 
         this.dataSource.data = this.predefinedAppointments;
       })
     });
  }
  schedulePredefinedAppointment(id: number) {
    let registeredUserId = parseInt(this.authService.getCurrentUser().userId);
    console.log(registeredUserId);
    this.appointmentService.schedulePredefinedAppointment(id, registeredUserId).subscribe({
      next: (res) => {
        Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: 'Appointment successfully scheduled!',
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
