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
        this.router.navigate(['scheduled-appointments']);
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
      this.predefinedAppointments.sort((a, b) => new Date(a.date).getTime() - new Date(b.date).getTime());
      return this.dataSource.data = this.predefinedAppointments;
    }

    else if (this.selectedOption == '2') {
      this.predefinedAppointments.sort(({ time: a }, { time: b }) => this.getNumber(a) - this.getNumber(b));
      return this.dataSource.data = this.predefinedAppointments;
    }
    else if (this.selectedOption == '3') {
      this.predefinedAppointments.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
      return this.dataSource.data = this.predefinedAppointments;
    }
    else if (this.selectedOption == '4') {
      this.predefinedAppointments.sort(({ time: a }, { time: b }) => this.getNumber(b) - this.getNumber(a));
      return this.dataSource.data = this.predefinedAppointments;
    }
    else {
      return null;
    }
  }

  getNumber(t: string) {
    return +t.replace(/:/g, '');
  }


}
