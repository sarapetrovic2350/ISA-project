import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatStepper } from '@angular/material/stepper';
import {MatStepperModule} from '@angular/material/stepper';
import{Report} from 'src/app/model/report.model';
import { User } from 'src/app/model/user.model';
import {QuestionnaireService} from 'src/app/service/questionnaire.service';
import { AuthService } from 'src/app/service/auth.service';
import { RegisterUserService } from 'src/app/service/register-user.service';
import {ReportService} from 'src/app/service/report.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.css']
})
export class CreateReportComponent implements OnInit {

  patient = new User();
  administrator = new User();
  selectedPresent: string = '';  
  report: Report = new Report(); 

  nesto: string = ''; 

  idUser = 2; 

  blodId: number = 0; 

  addReason: boolean = false; 

  firstStep : boolean = true
  secondStep : boolean = true
  thirdStep : boolean = true
  fourthStep : boolean = true

  formGroupBlood = new FormGroup({
    bloodIdd: new FormControl(), 
    quantaty: new FormControl(), 
    equipment: new FormControl(), 
  })

  formGroupPresent = new FormGroup({
    present : new FormControl()
  })

  formGroupReport = new FormGroup({
    heart : new FormControl(), 
    lungs : new FormControl(),
    weight : new FormControl(),
    height : new FormControl(),
    bloodPreasure : new FormControl(),
    status : new FormControl(), 
    haemoglobinValue: new FormControl(), 
    reason: new FormControl()
  })

  constructor(private toastr: ToastrService,private userService: RegisterUserService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService, private formBuilder: FormBuilder, 
    private questionsService: QuestionnaireService, private reportService: ReportService) { }

  ngOnInit(): void {
    this.administrator = this.authService.getCurrentUser();
    if (this.administrator != null) {
      this.userService.getUserByEmail(this.administrator.email).subscribe(res => {
        this.administrator = res;
      })
    }

    this.userService.getUserById(this.idUser).subscribe(res => {
      this.patient = res;
    })

    this.route.params.subscribe((params: Params) => {
      this.userService.getUserById(params['id']).subscribe(res => {
        this.patient = res;
        console.log(this.patient); 
      })
    });

    this.formGroupReport =this.formBuilder.group({
      heart : ["", Validators.required],
      lungs : ["", Validators.required],
      weight : ["", Validators.required], 
      height : ["", Validators.required],
      bloodPreasure : ["", Validators.required],
      status : ["", Validators.required], 
      haemoglobinValue: ["", Validators.required], 
      reason: ["", Validators.required]
    })
    this.formGroupBlood =this.formBuilder.group({
      bloodIdd: ["", Validators.required], 
      quantaty: ["", Validators.required], 
      equipment: ["", Validators.required], 
    });

    this.formGroupPresent = this.formBuilder.group({
      present: ["", Validators.required]
    });

  }

  check(){
    console.log(this.selectedPresent); 
    this.selectedPresent = this.formGroupPresent.value.present;
    if(this.selectedPresent == '0'){
      this.nesto = "NO"; 
    }else{

      this.nesto = "YES"; 
    }
    
    if(this.nesto == "NO"){
      // idi na sledecu stranu i pravi penal 
      this.userService.checkPenalties(Number(this.patient.userId), this.nesto).subscribe( 
        {
        error: (e) => {
          console.log(e);
          // predji na sledecu drugu strabicu 
          // prestani sa koracima 
          this.router.navigate(['/medical-centers']);
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Noted penalties!',
            })  
        }
        });
     }
     else{
      // proveri korisnikov upitnik 
      
      this.questionsService.checkQuestionnaire(Number(this.patient.userId)).subscribe( 
        {
        error: (e) => {
          console.log(e);
          // predji na sledecu drugu strabicu 
          // prestani sa koracima 
          this.router.navigate(['/medical-centers']);
            Swal.fire({
              icon: 'error',
              title: '',
              text: 'Patients questionnaire says he can not give blood!',
            })  
        }
        });
    }

  }

  newCheck(){
    // this.report.appointmentId = 1; 
    // this.report.customerId = this.idUser; 
    // this.report.administratorEmail = this.administrator.email; 
    // //this.report.bloodId = this.formGroupBlood.value.bloodIdd; 
    // this.report.bloodId = this.blodId
    // this.report.weight = this.formGroupReport.value.weight; 
    // this.report.haemoglobinValue = this.formGroupReport.value.haemoglobinValue;
    // this.report.heart =this.formGroupReport.value.heart;
    // this.report.lungs =this.formGroupReport.value.lungs;
    // this.report.height=this.formGroupReport.value.height
    // //this.report.bloodPreasure=this.formGroupReport.value.bloodPreasure; 
    // this.report.reportStatus="ACCEPTED"; 
    // this.report.quantaty= this.formGroupBlood.value.quantaty
    // this.report.reason= "Dobro je";  
    // this.report. equipmentQuantaty=this.formGroupBlood.value.equipment; 
    // this.report.present = this.formGroupPresent.value.present; 
    // console.log(this.report); 
    // if(this.report.reportStatus == "1"){
    //   this.reportService.createReport(this.report).subscribe( 
    //     {next: (res) => {
          
    //         Swal.fire({
    //           icon: 'success',
    //           title: 'Noted!',
    //           text: 'Noted!',
    //         })  
    //     },
    //     error: (e) => {
    //       this.router.navigate(['/medical-centers']);
    //       console.log(e);
    //         Swal.fire({
    //           icon: 'error',
    //           title: 'Oops...',
    //           text: 'Check the fields again!',
    //         })  
    //     }
    //     }); 
    // }
    
  }

  create(){
    this.report.appointmentId = 1; 
    this.report.customerId = Number(this.patient.userId); 
    this.report.administratorEmail = this.administrator.email; 
    //this.report.bloodId = this.formGroupBlood.value.bloodIdd; 
    this.report.bloodId = this.blodId
    this.report.weight = this.formGroupReport.value.weight; 
    this.report.haemoglobinValue = this.formGroupReport.value.haemoglobinValue;
    this.report.heart =this.formGroupReport.value.heart;
    this.report.lungs =this.formGroupReport.value.lungs;
    this.report.height=this.formGroupReport.value.height
    //this.report.bloodPreasure=this.formGroupReport.value.bloodPreasure; 
    this.report.reportStatus="ACCEPTED";  
    this.report.quantaty= this.formGroupBlood.value.quantaty
    this.report.reason= "Dobro je"; 
    this.report. equipmentQuantaty=this.formGroupBlood.value.equipment; 
    this.report.present = this.formGroupPresent.value.present; 
    this.reportService.createReport(this.report).subscribe( 
      {next: (res) => {
        this.router.navigate(['/medical-centers']);
          Swal.fire({
            icon: 'success',
            title: 'Noted!',
            text: 'Noted!',
          })  
      },
      error: (e) => {
        console.log(e);
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Check the fields again!',
          })  
      }
      }); 
  }

  chooseStatus(){

    if(this.formGroupReport.value.status == "1"){
      this.addReason = true; 
    }
  }

}
