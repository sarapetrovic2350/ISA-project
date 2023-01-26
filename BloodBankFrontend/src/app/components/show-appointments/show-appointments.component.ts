import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { AppointmentService } from 'src/app/service/appointment.service';
import { CenterAdministrator } from 'src/app/model/center-administrator.model';
import { AuthService } from 'src/app/service/auth.service';
import {ShowAppointments} from 'src/app/model/show-appointments.model';
import { CenterAdministratorService } from 'src/app/service/center-administrator.service'

@Component({
  selector: 'app-show-appointments',
  templateUrl: './show-appointments.component.html',
  styleUrls: ['./show-appointments.component.css']
})
export class ShowAppointmentsComponent implements OnInit {
  public dataSource = new MatTableDataSource<ShowAppointments>();
  public displayedColumns = ['Date', 'Time', 'Patient Name', 'Patient Surname','Status', 'Report'];
  public appointments: ShowAppointments[] = [];
  public appointment: ShowAppointments | undefined = undefined;
  public user = new User();
  public administrator: CenterAdministrator | undefined = undefined;
  public centerAdministrators: CenterAdministrator[] = [];
  public selectedOption: string = "";

  constructor(private appointmentService: AppointmentService,private centerAdministratorService: CenterAdministratorService,  private Router: Router, private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit(): void {

    this.user = this.authService.getCurrentUser();
      this.centerAdministratorService.findByEmail(this.user.email).subscribe(res => {
        this.administrator = res;
        //console.log(this.administrator); 
        
          this.appointmentService.getAllAppointmentsByCenterAdministrator(this.administrator.email).subscribe(data => {
            this.appointments = data;    
            this.dataSource.data = this.appointments;

          })
      })
  }

  public report(id: string){
    //this.idNum = Number(id); 
    this.Router.navigate(['/report/' + id + '/search']);
  }
}
