import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { medicalCenter } from 'src/app/model/medicalCenter.model';
import { MedicalCenterServiceService } from 'src/app/service/medical-center.service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register-medical-center',
  templateUrl: './register-medical-center.component.html',
  styleUrls: ['./register-medical-center.component.css']
})
export class RegisterMedicalCenterComponent implements OnInit {

  title = 'Create New Medical Center';
  medicalCenter = new medicalCenter();
  name:string =''
  street: string = "";
  streetNumber: string = "";
  city: string= "";
  country: string="";
  description:string =''
  averageGrade:number=0
  image: string = ''
  submitted = false;

  constructor(
    private router: Router,
    private medicalCenterService: MedicalCenterServiceService
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {

    //this.medicalCenter.address.street = this.street;
    //this.medicalCenter.address.streetNumber = this.streetNumber;
    //this.medicalCenter.address.city = this.city;
    //this.medicalCenter.address.country = this.country;
    //console.log(this.street);
    this.medicalCenterService.createMedicalCenter(this.medicalCenter).subscribe(
    
      {
        next: (res) => {
          //this.router.navigate(['/login']);
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Sucessfully registered!',
          })   
         
          
        
        }

    });
  }
}
