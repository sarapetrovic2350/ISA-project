import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { PredefAppointment } from 'src/app/model/predef-appointment.model';
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
  public dataSource = new MatTableDataSource<PredefAppointment>();
  public displayedColumns = ['date', 'time', 'duration', 'schedule'];
  public predefinedAppointments: PredefAppointment[] = [];
  public selectedOption: string = "";

  constructor(private route: ActivatedRoute, private router: Router, private appointmentService: AppointmentService, private authService: AuthService) { }

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
        this.router.navigate(['scheduled-appointments/' + registeredUserId]);
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
  changeSorting() {
    console.log(this.selectedOption);
    if (this.selectedOption == '1') {
      console.log(this.predefinedAppointments);
      
      return this.predefinedAppointments.sort((a, b) =>  new Date(b.date).getTime() - new Date(a.date).getTime());
      
    }

    else if (this.selectedOption == '2') {
      return this.predefinedAppointments.sort((a, b) => b.time.localeCompare(a.time));
    }
    else {
      return null;
    }
  }


}
