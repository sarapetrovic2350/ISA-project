import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { PredefinedAppointment } from 'src/app/model/predefined-appointment.model';
import { User } from 'src/app/model/user.model';
import { AppointmentService } from 'src/app/service/appointment.service';
import { AuthService } from 'src/app/service/auth.service';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registered-user-appointment',
  templateUrl: './registered-user-appointment.component.html',
  styleUrls: ['./registered-user-appointment.component.css']
})
export class RegisteredUserAppointmentComponent implements OnInit {
  appointmentRegisteredUserDTO = new PredefinedAppointment();
  medicalCenters: medicalCenter[] = [];
  user = new User();

  date: string= "";
  time: string= "00:00";
  firstStep : boolean = true
  secondStep : boolean = true
  public displayedColumns = ['name', 'address', 'grade', 'schedule'];
  title = "Medical Centers";
  public dataSource = new MatTableDataSource<any>();
  public selectedOption: string = "";

  formGroupAppointment = new FormGroup({
    date: new FormControl(), 
    time: new FormControl(), 
  })
  formGroupDate: any;

  

  constructor(private formBuilder: FormBuilder, private medicalCenterService: MedicalCenterServiceService, private appointmentService: AppointmentService,private authService: AuthService) { }

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();

    this.formGroupAppointment =this.formBuilder.group({
      date : ["", Validators.required],
      time : ["", Validators.required],
    })
  }

  getValidDate(date: string) {
    const datee = new Date(this.date);
    const dd = String(datee.getDate()).padStart(2, '0');
    const mm = String(datee.getMonth() + 1).padStart(2, '0');
    const yyyy = datee.getFullYear();

    this.date = yyyy + '-' + mm + '-' + dd;

    //this.dateOfBirth.setValue(yyyy + '-' + mm + '-' + dd);
    return yyyy + '-' + mm + '-' + dd;
  }
  getRequestParams(date: string, time: string): any {
    let params: any = {};

      params[`date`] = date;
      params[`time`] = time;

    return params;
}

  showMedicalCenters(){
    //this.date = this.getValidDate(this.date);
    const params = this.getRequestParams(this.date, this.time);
    this.medicalCenterService.getMedicalCentersWithAvailableAppointment(params).subscribe((data: any) => {
      this.medicalCenters = data;
      this.dataSource.data = this.medicalCenters;
      console.log(this.medicalCenters);
      console.log(this.date);
    })
  }

  changeSorting() {
    console.log(this.selectedOption);
    if (this.selectedOption == '1') {
      this.medicalCenters.sort((a, b) => b.averageGrade - a.averageGrade);
        return this.dataSource.data = this.medicalCenters
    }
    else if (this.selectedOption == '2') {
      this.medicalCenters.sort((a, b) => a.averageGrade - b.averageGrade);
        return this.dataSource.data = this.medicalCenters
    }
    else {
      return null;
    }
  }


  scheduleAppointment(id: string){
    this.appointmentRegisteredUserDTO.registeredUserID = this.user.userId;
    this.appointmentRegisteredUserDTO.date = this.date;
    this.appointmentRegisteredUserDTO.time = this.time;
    this.appointmentRegisteredUserDTO.medicalCenterID = id;
    //console.log(this.date);
    console.log(this.appointmentRegisteredUserDTO);

    this.appointmentService.createAppointmentRegisteredUser(this.appointmentRegisteredUserDTO).subscribe( 
      {next: (res) => {
        //this.router.navigate(['/update-user']);
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Sucessfully scheduled appointment!',
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

