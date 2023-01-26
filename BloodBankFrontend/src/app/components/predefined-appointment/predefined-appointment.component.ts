import { Component, OnInit } from '@angular/core';
import { PredefinedAppointment } from 'src/app/model/predefined-appointment.model';
import { User } from 'src/app/model/user.model';
import { AppointmentService } from 'src/app/service/appointment.service';
import { AuthService } from 'src/app/service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-predefined-appointment',
  templateUrl: './predefined-appointment.component.html',
  styleUrls: ['./predefined-appointment.component.css']
})
export class PredefinedAppointmentComponent implements OnInit {
  title = 'Make Appointments';
  appointment = new PredefinedAppointment();
  user = new User();
  message: string="";
  submitted = false;
  date: string= "";
  time: string= "00:00";


  constructor( private appointmentService: AppointmentService,private authService: AuthService) { }

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();
  }

  cretePredefinedAppointment(){
    this.appointment.administratorCenterID = this.user.userId;
    this.appointment.date = this.date;
    this.appointment.time = this.time;
    console.log(this.date);
    console.log(this.appointment);

    this.appointmentService.createPredefinedAppointment(this.appointment).subscribe( 
      {next: (res) => {
        //this.router.navigate(['/update-user']);
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Sucessfully created predefined appointment!',
          })  
      },
      error: (e) => {
        console.log(e);
        this.submitted = false;
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Check the fields again!',
          })  
      }
      });
  }
}
